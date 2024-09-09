package com.kimatesting.qa.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFileCreator {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static File createJsonFile(JsonNode jsonNode, String filePath) throws IOException {
        File jsonFile = new File(filePath);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, jsonNode);
        return jsonFile;
    }
}
