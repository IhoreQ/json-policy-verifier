package org.ibobek.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Policy(@JsonProperty("PolicyName") String policyName,
                     @JsonProperty(value = "PolicyDocument", required = true) PolicyDocument policyDocument) {
}