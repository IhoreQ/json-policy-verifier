package org.ibobek;

import org.json.JSONArray;
import org.json.JSONObject;

class PolicyStatementValidator {
    public boolean checkPolicyStatements(Object statementIntervention) throws Exception {
        if (statementIntervention instanceof JSONArray statement) {
            return processStatementArray(statement);
        } else if (statementIntervention instanceof JSONObject statement) {
            return isStatementObjectValid(statement);
        } else
            throw new Exception("Not valid statement object");
    }

    private boolean isStatementObjectValid(JSONObject statementObject) {
        Object resourceIntervention = statementObject.get("Resource");

        if (resourceIntervention instanceof String resource) {
            return isResourceFieldValid(resource);
        }
        return true;
    }

    private boolean processStatementArray(JSONArray statementArray) {
        for (int i = 0; i < statementArray.length(); ++i) {
            Object resourceIntervention = statementArray.optJSONObject(i).get("Resource");

            if (resourceIntervention instanceof String resource) {
                boolean result = isResourceFieldValid(resource);
                if (!result)
                    return false;
            }
        }
        return true;
    }

    public boolean isResourceFieldValid(String resource) {
        return !resource.equals("*");
    }
}