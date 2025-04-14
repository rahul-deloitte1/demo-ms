package com.everhealth.integration.zbfspublisher.modal;

public class ApplicationConstants {

    public static final String GRANT_TYPE = "client_credentials";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_URLENCODED = "application/x-www-form-urlencoded";

    public static final String OAUTH_TOKEN_URL = "https://rest.test.zuora.com/oauth/token";
    public static final String SUBSCRIPTION_URL = "https://rest.test.zuora.com/v1/subscriptions/";

    public static final String STATUS_MESSAGE_SUCCESS = "Published event successfully";
    public static final String INTEGRATION_STATUS_SUCCESS = "Send for Fulfillment";
    public static final String SUCCESS_MESSAGE_NEW = "New subscription details published successfully";

    public static final String ACTION_NEW = "NewSubscription";



    @Override
    public String toString() {
        return "ApplicationConstants{}";
    }
}
