package org.ibobek;

import org.ibobek.exception.MissingJSONFieldsException;
import org.ibobek.model.Policy;

import java.io.IOException;

public class PolicyService {
    public void processPolicy(String[] args) throws IOException, MissingJSONFieldsException {
        JSONReader jsonReader = new JSONReader();
        PolicyValidator policyValidator = new PolicyValidator();

        String filePath = args[0];
        Policy policy = jsonReader.getPolicyFromFile(filePath);
        System.out.println(policyValidator.validate(policy));
    }
}
