package com.kimatesting.qa;

import com.kimatesting.qa.ApiManager;
import com.kimatesting.qa.enums.FileType;
import com.kimatesting.qa.enums.Method;
import com.kimatesting.qa.utils.ConfigLoader;
import com.kimatesting.qa.utils.swagger.SwaggerTestGenerator;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SwaggerTesRunner extends ApiManager {

    private final String SWAGGER_URL="https://petstore.swagger.io/v2/swagger.json";
    private final String SWAGGER_NAME="petshop-swagger.json";
    private final String TEST_FOLDER_NAME="petshop";

    @Test(groups = {"swagger"}, description = "Generate Swagger Test")
    public void runSwaggerTestGenerator() throws Exception {
        setUrl(SWAGGER_URL);
        sendRequest(Method.GET);
        String filePath = ConfigLoader.getProperty("swaggerFolderPath")+SWAGGER_NAME;
        String responseBody = response.getBody().asString();
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SwaggerTestGenerator generator = new SwaggerTestGenerator();
        generator.generateTests(ConfigLoader.getProperty("swaggerFolderPath")+SWAGGER_NAME, FileType.JSON, TEST_FOLDER_NAME);
    }
}
