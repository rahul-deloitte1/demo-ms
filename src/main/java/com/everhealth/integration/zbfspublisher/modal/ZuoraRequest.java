package com.everhealth.integration.zbfspublisher.modal;

public class ZuoraRequest {
    private String orderId;
    private String subscriptionId;

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getSubscriptionId() {
        return subscriptionId;
    }
    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}