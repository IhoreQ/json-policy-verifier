package org.ibobek;

import org.ibobek.exception.MissingJSONFieldsException;
import org.ibobek.model.Policy;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Incorrect number of arguments. Expected 2 arguments.");
            return;
        }

        JSONReader jsonReader = new JSONReader();
        PolicyValidator policyValidator = new PolicyValidator();
        Policy policy;

        String filePath = args[0];

        try {
            policy = jsonReader.getPolicyFromFile(filePath);
        } catch (IOException e) {
            System.err.println("Provided invalid data");
            return;
        } catch (MissingJSONFieldsException e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println(policyValidator.validate(policy));
    }
}