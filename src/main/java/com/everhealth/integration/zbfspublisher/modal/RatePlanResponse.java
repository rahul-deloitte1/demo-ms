package com.everhealth.integration.zbfspublisher.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RatePlanResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("ratePlanCharges")
    private List<RatePlanChargeResponse> ratePlanCharges;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<RatePlanChargeResponse> getRatePlanCharges() {
        return ratePlanCharges;
    }

    public void setRatePlanCharges(List<RatePlanChargeResponse> ratePlanCharges) {
        this.ratePlanCharges = ratePlanCharges;
    }
}
