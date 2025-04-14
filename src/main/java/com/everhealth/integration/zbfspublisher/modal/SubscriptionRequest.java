package com.everhealth.integration.zbfspublisher.modal;

import java.util.List;
import com.everhealth.integration.zbfspublisher.modal.zuora.RatePlan;

public class SubscriptionRequest {

    private String eventId;
    private String action;
    private String accountNumber;
    private String accountName;
    private String orderNumber;
    private String subscriptionNumber;
    private Integer version;
    private String revision;
    private List<RatePlan> ratePlans;
    private String createdDateTime;
    private String updatedDateTime;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSubscriptionNumber() {
        return subscriptionNumber;
    }

    public void setSubscriptionNumber(String subscriptionNumber) {
        this.subscriptionNumber = subscriptionNumber;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public List<RatePlan> getRatePlans() {
        return ratePlans;
    }

    public void setRatePlans(List<RatePlan> ratePlans) {
        this.ratePlans = ratePlans;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    @Override
    public String toString() {
        return "SubscriptionRequest{" +
                "eventId='" + eventId + '\'' +
                ", action='" + action + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountName='" + accountName + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", subscriptionNumber='" + subscriptionNumber + '\'' +
                ", version='" + version + '\'' +
                ", revision='" + revision + '\'' +
                ", ratePlans=" + ratePlans +
                ", createdDateTime='" + createdDateTime + '\'' +
                ", updatedDateTime='" + updatedDateTime + '\'' +
                '}';
    }
}
