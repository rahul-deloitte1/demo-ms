package com.everhealth.integration.zbfspublisher.modal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateRatePlanChargeResponse {

    @JsonProperty("Id")
    private String Id;
    @JsonProperty("Success")
    private Boolean Success;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    @Override
    public String toString() {
        return "UpdateRatePlanChargeResponse{" +
                "Id='" + Id + '\'' +
                ", Success='" + Success + '\'' +
                '}';
    }
}
