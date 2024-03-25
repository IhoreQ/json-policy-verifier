package org.ibobek;

import org.json.JSONObject;

public class PolicyProcessor {
    private final JSONReader jsonReader;
    private final PolicyStatementValidator policyStatementValidator;

    public PolicyProcessor() {
        this.jsonReader = new JSONReader();
        this.policyStatementValidator = new PolicyStatementValidator();
    }

    public void processPolicy(String filePath) {
        JSONObject jsonObject = jsonReader.readJSONFromFile(filePath);
        if (jsonObject == null) {
            return;
        }

        Object statementIntervention = jsonReader.extractStatementObject(jsonObject);

        try {
            boolean result = policyStatementValidator.checkPolicyStatements(statementIntervention);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
