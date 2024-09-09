package com.kimatesting.qa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kimatesting.qa.enums.Method;
import com.kimatesting.qa.utils.ConfigLoader;
import com.kimatesting.qa.utils.Report;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.Assert;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiManager {

    protected static RequestSpecification request;
    protected static Response response;

    public static void setApiManager(String url) {
        request = given().contentType(ContentType.JSON);
        request.baseUri(url);
    }

    protected void setUrl(String baseUrl){
        request.baseUri(baseUrl);
    }

    protected void setUrlPath(String endpoint) {
        request.basePath(endpoint);
    }

    protected void setBearerAuthorization(String token){
        setHeader("Authorization", "Bearer "+ token);
    }

    private void setBasicAuthOAuthClient(String clientId, String clientSecret) {
        request.auth().preemptive().basic(clientId, clientSecret);
    }

    protected void setHeader(String key, Object value){
        request.header(key, value);
    }

    protected void setQueryParam(String key, Object value){
        request.queryParam(key, value);
    }

    protected  void setFileToBody(String filePath){
        try {
            String content = new String(Files.readAllBytes(Paths.get(ConfigLoader.getProperty("requestBodyFolderPath") +filePath)));
            request.body(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void replacePathParam(String oldValue, String newValue) {
        request.basePath(getBasePath().replace(String.format("{{%s}}",oldValue), newValue));
    }

    protected void sendRequest(Method method) {
        if (method.equals(Method.POST)) response = request.when().post();
        if (method.equals(Method.GET)) response = request.when().get();
        if (method.equals(Method.PUT)) response = request.when().put();
        if (method.equals(Method.DELETE)) response = request.when().delete();
        if (method.equals(Method.PATCH)) response = request.when().patch();
        try {
            addRequestAndResponseToReport();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addRequestAndResponseToReport() throws JsonProcessingException {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(request);
        Report.printRequestLogInReport(queryableRequestSpecification);
        Report.printResponseLogInReport(response);
    }

    /// GETTERS ///
    public static String getElementFromResponse(String path){
        return response.jsonPath().getString(path);
    }
    protected String getBasePath(){
        return ((RequestSpecificationImpl) request).getBasePath();
    }

    /// VALIDATORS ///
    protected void validateResponseSchema(String jsonSchemaPath) {
        response.then().body(matchesJsonSchemaInClasspath("jsonSchemas/"+jsonSchemaPath));
    }

    protected void validateExpectedStatus(Integer expectedStatus) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus);
    }

    protected void validateResponseContainsText(String path, String expectedMessage) {
        Assert.assertTrue(response.jsonPath().getString(path).contains(expectedMessage));
    }

}
