package com.kimatesting.qa.utils.swagger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.kimatesting.qa.enums.FileType;
import java.io.File;
import java.io.IOException;

public class SwaggerParse {

    public JsonNode readSwagger(String urlFile, FileType type) throws IOException {
        File file = new File(urlFile);

        if (!file.exists()) {
            throw new IOException("File not found: " + urlFile);
        }

        switch (type) {
            case JSON:
                return new ObjectMapper().readTree(file);
            case YAML:
                return new ObjectMapper(new YAMLFactory()).readTree(file);
            default:
                throw new IOException(String.format("File type %s is invalid.", type));
        }
    }
}
