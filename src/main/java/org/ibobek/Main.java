package org.ibobek;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Incorrect number of arguments. Expected 2 arguments.");
            return;
        }

        String filePath = args[0];

        JSONObject jsonObject = readJSONFromFile(filePath);

        if (jsonObject == null) {
            return;
        }

        Object statementIntervention = extractStatementObject(jsonObject);

        try {
            boolean result = checkPolicyStatements(statementIntervention);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private static boolean checkPolicyStatements(Object statementIntervention) throws Exception {

        if (statementIntervention instanceof JSONArray statement) {
            return processStatementArray(statement);
        } else if (statementIntervention instanceof JSONObject statement) {
            return isStatementObjectValid(statement);
        } else
            throw new Exception("Not valid statement object");
    }

    private static Object extractStatementObject(JSONObject jsonObject) {
        JSONObject policyDocument;
        Object statement;

        try {
            policyDocument = jsonObject.getJSONObject("PolicyDocument");
        } catch (JSONException e) {
            System.err.println("PolicyDocument key not found in JSON file");
            return null;
        }

        try {
            statement = policyDocument.get("Statement");
        } catch (JSONException e) {
            System.err.println("Statement key not found in JSON file");
            return null;
        }

        return statement;
    }

    private static boolean isStatementObjectValid(JSONObject statementObject) {
        Object resourceIntervention = statementObject.get("Resource");

        if (resourceIntervention instanceof String resource) {
            return isResourceFieldValid(resource);
        }

        return true;
    }

    private static boolean processStatementArray(JSONArray statementArray) {
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
    
    private static JSONObject readJSONFromFile(String filePath) {
        try {
            JSONTokener jsonTokener = new JSONTokener(new FileReader(filePath));
            return new JSONObject(jsonTokener);
        } catch (IOException e) {
            String[] pathElements = filePath.split("/");
            String fileName = pathElements[pathElements.length - 1];
            System.err.printf("File %s not found.", fileName);
        } catch (JSONException e) {
            System.err.println("JSON parsing error.");
        }

        return null;
    }

    public static boolean isResourceFieldValid(String resource) {
        return !resource.equals("*");
    }
}