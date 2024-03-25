package org.ibobek;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ibobek.exception.MissingJSONFieldsException;
import org.ibobek.model.Policy;

import java.io.IOException;

public class JSONTestReader {

    private final ObjectMapper objectMapper;

    public JSONTestReader() {
        this.objectMapper = new ObjectMapper();
    }

    public Policy getPolicyFromFile(String resourceName) throws IOException, MissingJSONFieldsException {
        ClassLoader classLoader = getClass().getClassLoader();
        Policy policy;

        try {
            policy = objectMapper.readValue(classLoader.getResource(resourceName), Policy.class);
        } catch (JsonMappingException | NullPointerException e) {
            throw new MissingJSONFieldsException("An error occurred during json parsing");
        }

        if (policy.getPolicyDocument() == null || policy.getPolicyDocument().getStatements() == null)
            throw new MissingJSONFieldsException("An error occurred during json parsing");

        return policy;
    }
}