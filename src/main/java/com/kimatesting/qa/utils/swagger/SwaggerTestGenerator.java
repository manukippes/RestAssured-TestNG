package com.kimatesting.qa.utils.swagger;

import com.fasterxml.jackson.databind.JsonNode;
import com.kimatesting.qa.enums.FileType;
import com.kimatesting.qa.utils.ConfigLoader;
import com.kimatesting.qa.utils.JsonFileCreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwaggerTestGenerator {

    private SwaggerParse swaggerParse;
    private SwaggerProcessor swaggerProcessor;

    public SwaggerTestGenerator() {
        this.swaggerParse = new SwaggerParse();
    }

    public void generateTests(String swaggerFilePath, FileType fileType, String folderName) throws IOException {
        JsonNode swaggerJson = swaggerParse.readSwagger(swaggerFilePath, fileType);
        this.swaggerProcessor = new SwaggerProcessor(swaggerJson);

        Map<String, Map<String, JsonNode>> endpoints = swaggerProcessor.getEndpoints();

        for (Map.Entry<String, Map<String, JsonNode>> endpointEntry : endpoints.entrySet()) {
            String endpointPath = endpointEntry.getKey();
            Map<String, JsonNode> endpoint = endpointEntry.getValue();
            generateTestForEndpoint(endpointPath, endpoint, folderName);
        }
    }

    private void generateTestForEndpoint(String endpoint, Map<String, JsonNode> methods, String folderName) {
        String className = convertToCamelCase(endpoint.replace("/", "").replace("{", "").replace("}", ""));

        StringBuilder classContent = generatedClassHeader(folderName, className);

        for (Map.Entry<String, JsonNode> methodEntry : methods.entrySet()) {
            String method = methodEntry.getKey().toUpperCase();
            String description = methodEntry.getValue().get("summary").toString().trim().replace("\"", "");
            String methodName = description.replace(" ", "");
            JsonNode methodDetails = methodEntry.getValue();
            String tags = methodDetails.get("tags").toString().replaceAll("[\\[\\]\"]","");
            JsonNode responses = methodDetails.get("responses");

            Iterator<Map.Entry<String, JsonNode>> fields = responses.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String statusCode = field.getKey();

                generatedMethodHeader(endpoint, className, classContent, method, description, methodName, tags, statusCode);
                generateBody(className, classContent);
                if (statusCode.equalsIgnoreCase("default")) generatedTODODefaultStatus(classContent, method);
                else generatedValidation(classContent, method, statusCode);
            }
        }
        classContent.append("}");

        try (FileWriter writer = new FileWriter(String.format(ConfigLoader.getProperty("packagePath")+"tests/%1$s/%2$sTest.java", folderName, className))) {
            writer.write(classContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder generatedClassHeader(String folderName, String className) {
        StringBuilder classContent = new StringBuilder(String.format("package com.kimatesting.qa.tests.%s;\n\n", folderName) +
                "import com.kimatesting.qa.ApiManager;\n" +
                "import org.testng.annotations.Test;\n" +
                "import com.kimatesting.qa.enums.Method;\n" +
                "import java.io.File;\n" +
                "public class " + className + "Test extends ApiManager {\n\n");
        return classContent;
    }
    private static void generatedMethodHeader(String endpoint, String className, StringBuilder classContent, String method, String description, String methodName, String tags, String statusCode) {
        classContent.append("    @Test(groups={\"" + tags + "\"}, description=\"" + description + " - Status Code " +statusCode+ "\")\n" +
                "    public void test" + className + method + methodName + statusCode + "() {\n" +
                "        setUrlPath(\"" + endpoint + "\");\n");
        if (endpoint.contains("{")) {
            Matcher matcher = Pattern.compile("\\{([^}]+)\\}").matcher(endpoint);
            while (matcher.find()) {
                String pathParam = matcher.group(1);
                classContent.append("        replacePathParam(\"" + pathParam + "\", \"value\");\n");
            }
        }
    }

    private void generateBody(String className, StringBuilder classContent) {
        JsonNode example = swaggerProcessor.getExample(className);
        if (example != null) {
            String jsonFilePath = className + "RequestBody.json";
            try {
                JsonFileCreator.createJsonFile(example, jsonFilePath);
                classContent.append("        setFileToBody(\"" + jsonFilePath + "\");\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generatedValidation(StringBuilder classContent, String method, String statusCode) {
        classContent.append("        sendRequest(Method." + method + ");\n" +
                "        validateExpectedStatus(" + statusCode + ");\n" +
                "    }\n\n");
    }
    private static void generatedTODODefaultStatus(StringBuilder classContent, String method) {
        classContent.append("        sendRequest(Method." + method + ");\n" +
                "        // TODO: Check default status code \n" +
                "    }\n\n");
    }
    private String convertToCamelCase(String text) {
        String[] parts = text.split("-");
        StringBuilder camelCaseString = new StringBuilder();
        for (String part : parts) {
            camelCaseString.append(part.substring(0, 1).toUpperCase()).append(part.substring(1).toLowerCase());
        }
        return camelCaseString.toString();
    }
}