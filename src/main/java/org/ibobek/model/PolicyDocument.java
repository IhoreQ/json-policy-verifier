package org.ibobek.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PolicyDocument(@JsonProperty("Version") String version,
                             @JsonProperty(value = "Statement", required = true) List<Statement> statements) {
}
