package com.everhealth.integration.zbfspublisher.service;

import com.everhealth.integration.zbfspublisher.modal.SubscriptionRequest;
import com.everhealth.integration.zbfspublisher.modal.SubscriptionResponse;

public interface SubscriptionService {

    SubscriptionResponse newSubscriptionDetails(SubscriptionRequest subscriptionRequest);
}
