package org.ibobek;

import org.ibobek.exception.MissingJSONFieldsException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PolicyService policyService = new PolicyService();

        try {
            policyService.processPolicy(args);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Please provide a file path as an argument.");
        } catch (IOException e) {
            System.err.println("Provided invalid data");
        } catch (MissingJSONFieldsException e) {
            System.err.println(e.getMessage());
        }
    }
}