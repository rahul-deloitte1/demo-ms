package com.everhealth.integration.zbfspublisher.service.impl;

import com.everhealth.integration.zbfspublisher.modal.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FetchSubscriptionServiceImpl {

    Logger LOG = LoggerFactory.getLogger(FetchSubscriptionServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    public String getSubscriptionDetails(String accessToken, String subscriptionNumber){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                ApplicationConstants.SUBSCRIPTION_URL + subscriptionNumber,
                HttpMethod.GET,
                request,
                String.class
        );

        return response.getBody().toString();
    }

}
