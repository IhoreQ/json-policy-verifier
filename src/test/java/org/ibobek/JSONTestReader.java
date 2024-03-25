package org.ibobek;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ibobek.exception.MissingJSONFieldsException;
import org.ibobek.model.Policy;

import java.io.IOException;

public class JSONTestReader {

    private final ObjectMapper objectMapper;

    public JSONTestReader() {
        this.objectMapper = new ObjectMapper()
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    public Policy getPolicyFromFile(String resourceName) throws IOException, MissingJSONFieldsException {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            return objectMapper.readValue(classLoader.getResource(resourceName), Policy.class);
        } catch (JsonMappingException e) {
            throw new MissingJSONFieldsException("An error occurred during json parsing");
        }
    }
}