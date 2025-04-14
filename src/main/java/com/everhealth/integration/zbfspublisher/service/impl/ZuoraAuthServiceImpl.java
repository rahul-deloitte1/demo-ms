package com.everhealth.integration.zbfspublisher.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.everhealth.integration.zbfspublisher.modal.ApplicationConstants;

@Service
public class ZuoraAuthServiceImpl {

    Logger LOG = LoggerFactory.getLogger(ZuoraAuthServiceImpl.class);

    private static final String CLIENT_ID = "0ddd4cfd-a83e-4fa6-ac5c-c127de8ffbb6";
    private static final String CLIENT_SECRET = "mYDYQO+arOQ8ImNX2YbkjLl8k8txE241ErOK6s";

    @Autowired
    RestTemplate restTemplate;

    public String getOAuthToken() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("client_id", CLIENT_ID);
        form.add("client_secret", CLIENT_SECRET);
        form.add("grant_type", ApplicationConstants.GRANT_TYPE);

        HttpHeaders headers = new HttpHeaders();
        headers.add(ApplicationConstants.CONTENT_TYPE, ApplicationConstants.CONTENT_TYPE_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);
        ResponseEntity<Object> responseEntity;
        String accessToken = null;

        try {
            responseEntity =
                    restTemplate.postForEntity(ApplicationConstants.OAUTH_TOKEN_URL, request, Object.class);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(responseEntity.getBody());

            JsonNode jsonNode = objectMapper.readTree(jsonString);
            accessToken = jsonNode.get("access_token").asText();

        } catch (Exception e) {
            LOG.error("Exception occurred while generating access token: ", e);
        }
        return accessToken;
    }
}
