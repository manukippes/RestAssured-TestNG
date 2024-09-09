package com.kimatesting.qa.utils.swagger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SwaggerProcessor {

    private final JsonNode swaggerJson;

    public SwaggerProcessor(JsonNode swaggerJson) {
        this.swaggerJson = swaggerJson;
    }

    public Map<String, Map<String, JsonNode>> getEndpoints() {
        Map<String, Map<String, JsonNode>> endpoints = new HashMap<>();
        JsonNode pathsNode = swaggerJson.get("paths");
        if (pathsNode != null) {
            Iterator<Map.Entry<String, JsonNode>> paths = pathsNode.fields();

            while (paths.hasNext()) {
                Map.Entry<String, JsonNode> path = paths.next();
                String endpoint = path.getKey();
                JsonNode methodsNode = path.getValue();
                Map<String, JsonNode> methods = new HashMap<>();
                methodsNode.fieldNames().forEachRemaining(method -> {
                    methods.put(method.toUpperCase(), methodsNode.get(method));
                });
                endpoints.put(endpoint, methods);
            }
        }

        return endpoints;
    }

    public JsonNode getExample(String endpoint) {
        JsonNode componentsNode = swaggerJson.get("components");
        if (componentsNode != null) {
            JsonNode endpointExample = componentsNode.get("schemas").get(endpoint);
            if (endpointExample != null) {
                JsonNode properties = endpointExample.get("properties");
                if (properties != null) {
                    ObjectNode exampleNode = new ObjectMapper().createObjectNode();

                    properties.fieldNames().forEachRemaining(propertyName -> {
                        JsonNode propertyNode = properties.get(propertyName);
                        if (propertyNode.has("example")) {
                            exampleNode.set(propertyName, propertyNode.get("example"));
                        } else if (propertyNode.has("$ref")) {
                            String ref = propertyNode.get("$ref").asText();
                            String refSchemaName = ref.substring(ref.lastIndexOf('/') + 1);
                            exampleNode.set(propertyName, getExample(refSchemaName));
                        } else if (propertyNode.has("type") && propertyNode.get("type").asText().equals("array")) {
                            ArrayNode arrayNode = new ObjectMapper().createArrayNode();
                            JsonNode itemsNode = propertyNode.get("items");
                            if (itemsNode.has("type")) {
                                if (itemsNode.has("example")) {
                                    arrayNode.add(itemsNode.get("example"));
                                }
                            } else if (itemsNode.has("$ref")) {
                                String ref = itemsNode.get("$ref").asText();
                                String refSchemaName = ref.substring(ref.lastIndexOf('/') + 1);
                                arrayNode.add(getExample(refSchemaName));
                            }
                            exampleNode.set(propertyName, arrayNode);
                        } else if (propertyNode.has("enum")) {
                            exampleNode.set(propertyName, propertyNode.get("enum").elements().next());
                        }
                    });

                    return exampleNode;
                }
            }
        }
        return null;
    }
}
