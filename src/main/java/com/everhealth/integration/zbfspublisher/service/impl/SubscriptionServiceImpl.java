package com.everhealth.integration.zbfspublisher.service.impl;

import com.everhealth.integration.zbfspublisher.feign.ZuoraClient;
import com.everhealth.integration.zbfspublisher.modal.*;
import com.everhealth.integration.zbfspublisher.modal.zuora.RatePlan;
import com.everhealth.integration.zbfspublisher.modal.zuora.RatePlanCharge;
import com.everhealth.integration.zbfspublisher.modal.zuora.Subscription;
import com.everhealth.integration.zbfspublisher.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    Logger LOG = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    private final ZuoraAuthServiceImpl zuoraAuthServiceImpl;

    @Autowired
    private ZuoraClient zuoraClient;

    public SubscriptionServiceImpl(ZuoraAuthServiceImpl zuoraAuthServiceImpl) {
        this.zuoraAuthServiceImpl = zuoraAuthServiceImpl;
    }

    @Override
    public SubscriptionResponse newSubscriptionDetails(SubscriptionRequest subscriptionRequest) {

        SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
        String subscriptionNumber = subscriptionRequest.getSubscriptionNumber();

        // Generate access token
        String accessToken = zuoraAuthServiceImpl.getOAuthToken();
        LOG.info("Retrieved oauth token is: {}", accessToken);

        // Invoke ZB api to read subscription details
        Subscription subscription = zuoraClient
                .getSubscriptionDetails("Bearer " + accessToken, subscriptionNumber);
        LOG.info("Feign response from Zuora : {}", subscription);

        // Construct Salesforce task request payload
        if (null!= subscription && subscription.getSuccess() == Boolean.TRUE) {
            subscriptionRequest = constructNewSubscriptionRequestPayload(subscription, subscriptionRequest);
            LOG.info("New Subscription Request Payload : {}", subscriptionRequest);

            // TODO - Logic to create a task in Salesforce

            // If task creation in SF is success, then update status back to ZB
            boolean isSuccessFlag = updateIntegrationStatus(subscription, accessToken);

            // Read updated response from Zuora & construct response object
            if(isSuccessFlag) {
                Subscription updatedZbResponse = zuoraClient
                        .getSubscriptionDetails("Bearer " + accessToken, subscriptionNumber);
                subscriptionResponse = constructSubscriptionResponse(subscriptionRequest, updatedZbResponse);
                LOG.info("New subscription response: {}", subscriptionResponse);
            }
        }
        else{
            LOG.warn("Received null response from Zuora");
        }

        return subscriptionResponse;
    }

    /**
     * Method to update integration status at rate plan charge level
     *
     * @param subscription subscription
     * @param accessToken accessToken
     * @return isSuccess flag
     */
    private boolean updateIntegrationStatus(Subscription subscription, String accessToken) {

        AtomicBoolean isSuccess = new AtomicBoolean(true);

        UpdateRatePlanChargeRequest updateRatePlanChargeRequest = new UpdateRatePlanChargeRequest();
        updateRatePlanChargeRequest.setIntegrationStatus__c(ApplicationConstants.INTEGRATION_STATUS_SUCCESS);

        List<RatePlan> ratePlanList = subscription.getRatePlans();
        ratePlanList.forEach(ratePlan -> {
            List<RatePlanCharge> ratePlanCharges = ratePlan.getRatePlanCharges();
            List<String> ratePlanChargeIds = ratePlanCharges.stream()
                    .map(RatePlanCharge::getId).toList();
            ratePlanChargeIds.forEach(id -> {
                UpdateRatePlanChargeResponse updateResponse = zuoraClient
                        .updateRatePlanCharge("Bearer " + accessToken, id, Boolean.FALSE, updateRatePlanChargeRequest);
                LOG.info("Feign response to update rate plan charge: {}", updateResponse);
                if (!updateResponse.getSuccess().equals(Boolean.TRUE)){
                    isSuccess.set(false);
                }
            });
        });
        return isSuccess.get();
    }


    /**
     * Method to construct request payload for SF task
     *
     * @param subscription subscription
     * @return SubscriptionRequest
     */
    public SubscriptionRequest constructNewSubscriptionRequestPayload
    (Subscription subscription, SubscriptionRequest subscriptionRequest) {
        if (subscriptionRequest.getSubscriptionNumber().equals(subscription.getSubscriptionNumber())) {

            subscriptionRequest.setEventId(UUID.randomUUID().toString());
            subscriptionRequest.setAction(ApplicationConstants.ACTION_NEW);
            subscriptionRequest.setAccountNumber(subscription.getAccountNumber());
            subscriptionRequest.setAccountName(subscription.getAccountName());
            subscriptionRequest.setVersion(subscription.getVersion());
            subscriptionRequest.setRevision(subscription.getRevision());
            subscriptionRequest.setRatePlans(subscription.getRatePlans());
            subscriptionRequest.setCreatedDateTime(subscription.getCreateTime());
            subscriptionRequest.setUpdatedDateTime(subscription.getUpdateTime());
        }

        return subscriptionRequest;
    }

    /**
     * Method to construct response object
     * to send back to Zuora Billing
     *
     * @param updatedZbResponse Subscription
     * @return SubscriptionResponse
     */
    public SubscriptionResponse constructSubscriptionResponse(
            SubscriptionRequest request, Subscription updatedZbResponse){

        SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
        subscriptionResponse.setSuccess(Boolean.TRUE);
        subscriptionResponse.setEventId(request.getEventId());
        subscriptionResponse.setAction(request.getAction());
        subscriptionResponse.setMessage(ApplicationConstants.SUCCESS_MESSAGE_NEW);
        subscriptionResponse.setSubscriptionNumber(request.getSubscriptionNumber());
        subscriptionResponse.setVersion(request.getVersion());
        subscriptionResponse.setRevision(request.getRevision());
        subscriptionResponse.setRatePlans(populateRatePlanResponseList(updatedZbResponse));
        subscriptionResponse.setTimestamp(Instant.now().toString());

        return subscriptionResponse;
    }

    /**
     * Method to populate RatePlanResponseList
     *
     * @param updatedZbResponse updatedZbResponse
     * @return ratePlanList
     */
    private static List<RatePlanResponse> populateRatePlanResponseList(Subscription updatedZbResponse) {
        List<RatePlanResponse> ratePlanList = new ArrayList<>();

        updatedZbResponse.getRatePlans().forEach(ratePlan -> {
            RatePlanResponse ratePlanResponse = new RatePlanResponse();
            ratePlanResponse.setId(ratePlan.getId());

            List<RatePlanChargeResponse> ratePlanChargeResponseList = new ArrayList<>();

            ratePlan.getRatePlanCharges().forEach(ratePlanCharge -> {
                RatePlanChargeResponse ratePlanChargeResponse = new RatePlanChargeResponse();
                ratePlanChargeResponse.setId(ratePlanCharge.getId());
                ratePlanChargeResponse.setIntegrationStatus__c(ratePlanCharge.getIntegrationStatus__c());

                ratePlanChargeResponseList.add(ratePlanChargeResponse);
            });

            ratePlanResponse.setRatePlanCharges(ratePlanChargeResponseList);
            ratePlanList.add(ratePlanResponse);
            });
        return ratePlanList;
    }
}
