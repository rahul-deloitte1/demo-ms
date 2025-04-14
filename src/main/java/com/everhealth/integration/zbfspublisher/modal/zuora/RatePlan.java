package com.everhealth.integration.zbfspublisher.modal.zuora;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RatePlan {

    @JsonProperty("id")
    private String id;
    @JsonProperty("ratePlanCharges")
    private List<RatePlanCharge> ratePlanCharges;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<RatePlanCharge> getRatePlanCharges() {
        return ratePlanCharges;
    }

    public void setRatePlanCharges(List<RatePlanCharge> ratePlanCharges) {
        this.ratePlanCharges = ratePlanCharges;
    }
}
