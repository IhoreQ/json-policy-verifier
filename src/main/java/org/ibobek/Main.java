package org.ibobek;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Incorrect number of arguments. Expected 2 arguments.");
            return;
        }

        String filePath = args[0];

        PolicyProcessor policyProcessor = new PolicyProcessor();
        policyProcessor.processPolicy(filePath);
    }
}