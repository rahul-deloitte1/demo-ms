package com.everhealth.integration.zbfspublisher.modal.zuora;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class RatePlanCharge {

    @JsonProperty("id")
    private String id;
    @JsonProperty("number")
    private String number;
    @JsonProperty("name")
    private String name;
    @JsonProperty("originalListPrice")
    private BigDecimal originalListPrice;
    @JsonProperty("quantity")
    private BigDecimal quantity;
    @JsonProperty("effectiveStartDate")
    private String effectiveStartDate;
    @JsonProperty("effectiveEndDate")
    private String effectiveEndDate;
    @JsonProperty("IntegrationStatus__c")
    private String IntegrationStatus__c;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOriginalListPrice() {
        return originalListPrice;
    }

    public void setOriginalListPrice(BigDecimal originalListPrice) {
        this.originalListPrice = originalListPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(String effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public String getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(String effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public String getIntegrationStatus__c() {
        return IntegrationStatus__c;
    }

    public void setIntegrationStatus__c(String IntegrationStatus__c) {
        this.IntegrationStatus__c = IntegrationStatus__c;
    }
}
