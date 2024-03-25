package org.ibobek;

import org.ibobek.model.Policy;
import org.ibobek.model.PolicyDocument;
import org.ibobek.model.Statement;

import java.util.List;

public class PolicyValidator {

    public boolean validate(Policy policy) {

        PolicyDocument policyDocument = policy.policyDocument();
        List<Statement> statements = policyDocument.statements();

        return statements.stream()
                .filter(statement -> statement.resources().size() == 1)
                .flatMap(statement -> statement.resources().stream())
                .noneMatch(this::isResourceInvalid);
    }

    private boolean isResourceInvalid(String resource) {
        return resource.equals("*");
    }
}
