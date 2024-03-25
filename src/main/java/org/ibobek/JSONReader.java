package org.ibobek;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ibobek.exception.MissingJSONFieldsException;
import org.ibobek.model.Policy;

import java.io.File;
import java.io.IOException;

class JSONReader {
    private final ObjectMapper objectMapper;

    public JSONReader() {
        this.objectMapper = new ObjectMapper();
    }

    public Policy getPolicyFromFile(String filePath) throws IOException, MissingJSONFieldsException {

        Policy policy;
        try {
            policy = objectMapper.readValue(new File(filePath), Policy.class);
        } catch (JsonMappingException | NullPointerException e) {
            throw new MissingJSONFieldsException("An error occurred during json parsing");
        }

        if (policy.getPolicyDocument() == null || policy.getPolicyDocument().getStatements() == null)
            throw new MissingJSONFieldsException("An error occurred during JSON parsing");

        return policy;
    }
}