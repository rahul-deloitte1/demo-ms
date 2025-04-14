package com.everhealth.integration.zbfspublisher.util;

public final class ValidationHelper {

    public boolean isValidSubscriptionNumber(
            String subscriptionNumber){
        return subscriptionNumber != null && subscriptionNumber.matches("^[A-Z0-9-]+$");
    }

    public boolean isValidOrderNumber(
            String orderNumber){
        return orderNumber != null && orderNumber.matches("^[A-Z0-9-]+$");
    }
}
