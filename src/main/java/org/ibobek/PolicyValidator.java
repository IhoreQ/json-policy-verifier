package org.ibobek;

import org.ibobek.model.Policy;
import org.ibobek.model.PolicyDocument;
import org.ibobek.model.Statement;

import java.util.List;

public class PolicyValidator {

    public boolean validate(Policy policy) {

        PolicyDocument policyDocument = policy.getPolicyDocument();
        List<Statement> statements = policyDocument.getStatements();

        for (Statement statement : statements) {
            if (statement.getResources().size() == 1) {
                for (String resource : statement.getResources()) {
                    if (isResourceInvalid(resource))
                        return false;
                }
            }
        }

        return true;
    }

    private boolean isResourceInvalid(String resource) {
        return resource.equals("*");
    }
}
