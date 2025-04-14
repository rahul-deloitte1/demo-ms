package com.everhealth.integration.zbfspublisher.feign;

import com.everhealth.integration.zbfspublisher.modal.UpdateRatePlanChargeRequest;
import com.everhealth.integration.zbfspublisher.modal.UpdateRatePlanChargeResponse;
import com.everhealth.integration.zbfspublisher.modal.zuora.Subscription;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "zuoraClient", url = "https://rest.test.zuora.com")
public interface ZuoraClient {

    @GetMapping("/v1/subscriptions/{subscriptionNumber}")
    Subscription getSubscriptionDetails(@RequestHeader("Authorization") String authorization,
                                        @PathVariable("subscriptionNumber") String subscriptionNumber);

    @PutMapping("/v1/object/rate-plan-charge/{id}")
    UpdateRatePlanChargeResponse updateRatePlanCharge(@RequestHeader("Authorization") String authorization,
                                                      @PathVariable("id") String id,
                                                      @RequestParam("rejectUnknownFields") Boolean rejectUnknownFields,
                                                      @RequestBody UpdateRatePlanChargeRequest updateRatePlanChargeRequest);
}
