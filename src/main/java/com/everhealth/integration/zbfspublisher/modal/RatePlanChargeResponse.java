package com.everhealth.integration.zbfspublisher.modal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RatePlanChargeResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("IntegrationStatus__c")
    private String integrationStatus__c;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntegrationStatus__c() {
        return integrationStatus__c;
    }

    public void setIntegrationStatus__c(String integrationStatus__c) {
        this.integrationStatus__c = integrationStatus__c;
    }

    @Override
    public String toString() {
        return "RatePlanChargeResponse{" +
                "id='" + id + '\'' +
                ", integrationStatus__c='" + integrationStatus__c + '\'' +
                '}';
    }
}
