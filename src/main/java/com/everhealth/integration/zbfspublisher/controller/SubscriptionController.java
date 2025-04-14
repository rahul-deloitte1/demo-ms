package com.everhealth.integration.zbfspublisher.controller;

import com.everhealth.integration.zbfspublisher.exception.InvalidRequestException;
import com.everhealth.integration.zbfspublisher.modal.SubscriptionRequest;
import com.everhealth.integration.zbfspublisher.modal.SubscriptionResponse;
import com.everhealth.integration.zbfspublisher.service.SubscriptionService;
import com.everhealth.integration.zbfspublisher.util.ValidationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SubscriptionController {

    Logger LOG = LoggerFactory.getLogger(SubscriptionController.class);

    public ValidationHelper validationHelper = new ValidationHelper();

    @Autowired
    public SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/v1/subscription/new")
    public ResponseEntity<SubscriptionResponse> newSubscription(@RequestBody SubscriptionRequest subscriptionRequest){

        LOG.info("SubscriptionController - Inside newSubscription");
        SubscriptionResponse subscriptionResponse = null;
        if(validationHelper.isValidSubscriptionNumber(subscriptionRequest.getSubscriptionNumber())
                && validationHelper.isValidOrderNumber(subscriptionRequest.getOrderNumber())){
            subscriptionResponse = subscriptionService.newSubscriptionDetails(subscriptionRequest);
        }
        else{
            LOG.warn("Request Subscription number is invalid");
            throw new InvalidRequestException("Request Subscription number is invalid");
        }

        LOG.info("SubscriptionController - Exiting newSubscription");
        return new ResponseEntity<>(subscriptionResponse, HttpStatus.OK);

    }
}
