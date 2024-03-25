package org.ibobek.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Statement(@JsonProperty("Effect") String effect,
                        @JsonProperty("Action") List<String> actions,
                        @JsonProperty(value = "Resource", required = true) List<String> resources) {
}
