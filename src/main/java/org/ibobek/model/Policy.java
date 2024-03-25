package org.ibobek.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Policy {

    @JsonProperty("PolicyName")
    private String policyName;

    @JsonProperty(value = "PolicyDocument", required = true)
    private PolicyDocument policyDocument;

    public PolicyDocument getPolicyDocument() {
        return policyDocument;
    }
}