package org.ibobek;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;

class JSONReader {
    public JSONObject readJSONFromFile(String filePath) {
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

    public Object extractStatementObject(JSONObject jsonObject) {
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
}