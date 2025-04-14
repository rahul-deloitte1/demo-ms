package com.everhealth.integration.zbfspublisher.modal;

import java.util.List;

public class SubscriptionResponse {

    private Boolean success;
    private String eventId;
    private String action;
    private String message;
    private String subscriptionNumber;
    private Integer version;
    private String revision;
    private List<RatePlanResponse> ratePlans;
    private String timestamp;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public List<RatePlanResponse> getRatePlans() {
        return ratePlans;
    }

    public void setRatePlans(List<RatePlanResponse> ratePlans) {
        this.ratePlans = ratePlans;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SubscriptionResponse{" +
                "success=" + success +
                ", eventId='" + eventId + '\'' +
                ", action='" + action + '\'' +
                ", message='" + message + '\'' +
                ", subscriptionNumber='" + subscriptionNumber + '\'' +
                ", version=" + version +
                ", revision='" + revision + '\'' +
                ", ratePlans=" + ratePlans +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
