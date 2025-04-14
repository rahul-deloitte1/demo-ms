package com.everhealth.integration.zbfspublisher.modal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class UpdateRatePlanChargeRequest {

    @JsonProperty("IntegrationStatus__c")
    private String IntegrationStatus__c;

    public String getIntegrationStatus__c() {
        return IntegrationStatus__c;
    }

    public void setIntegrationStatus__c(String integrationStatus__c) {
        IntegrationStatus__c = integrationStatus__c;
    }
}
