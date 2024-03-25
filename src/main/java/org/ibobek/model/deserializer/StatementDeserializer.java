package org.ibobek.model.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.ibobek.model.Statement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatementDeserializer extends StdDeserializer<Object> {

    public StatementDeserializer() {
        super(Object.class);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        if (node.isArray()) {
            List<Statement> statements = new ArrayList<>();
            for (JsonNode statementNode : node) {
                Statement statement = getStatementFromNode(statementNode);
                statements.add(statement);
            }
            return statements;
        } else {
            Statement statement = getStatementFromNode(node);
            return Collections.singletonList(statement);
        }
    }

    private Statement getStatementFromNode(JsonNode node) {
        Statement statement = new Statement();
        statement.setEffect(node.get("Effect").asText());

        List<String> actions = getActionsFromNode(node.get("Action"));
        statement.setAction(actions);

        List<String> resources = getResourcesFromNode(node.get("Resource"));
        statement.setResource(resources);

        return statement;
    }

    private List<String> getActionsFromNode(JsonNode node) {
        List<String> actions = new ArrayList<>();
        for (JsonNode action : node) {
            actions.add(action.asText());
        }
        return actions;
    }

    private List<String> getResourcesFromNode(JsonNode node) {
        if (node.isArray()) {
            List<String> resources = new ArrayList<>();
            for (JsonNode resourceNode : node) {
                resources.add(resourceNode.asText());
            }
            return resources;
        } else {
            return Collections.singletonList(node.asText());
        }
    }
}
