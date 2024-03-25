package org.ibobek.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.ibobek.model.deserializer.StatementDeserializer;

import java.util.List;

public class PolicyDocument {
    @JsonProperty("Version")
    private String version;

    @JsonProperty("Statement")
    @JsonDeserialize(using = StatementDeserializer.class)
    private List<Statement> statements;

    public List<Statement> getStatements() {
        return statements;
    }
}
