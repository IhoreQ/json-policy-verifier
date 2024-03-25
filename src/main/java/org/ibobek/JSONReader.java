package org.ibobek;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ibobek.exception.MissingJSONFieldsException;
import org.ibobek.model.Policy;

import java.io.File;
import java.io.IOException;

public class JSONReader {
    private final ObjectMapper objectMapper;

    public JSONReader() {
        this.objectMapper = new ObjectMapper()
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    public Policy getPolicyFromFile(String filePath) throws IOException, MissingJSONFieldsException {
        try {
            return objectMapper.readValue(new File(filePath), Policy.class);
        } catch (JsonMappingException e) {
            throw new MissingJSONFieldsException("An error occurred during json parsing");
        }
    }
}