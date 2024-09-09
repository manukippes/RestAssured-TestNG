package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class PetpetidTest extends ApiManager {

    @Test(groups={"pet"}, description="Deletes a pet - Status Code 400")
    public void testPetpetidDELETEDeletesapet400() {
        setUrlPath("/pet/{petId}");
        replacePathParam("petId", "value");
        sendRequest(Method.DELETE);
        validateExpectedStatus(400);
    }

    @Test(groups={"pet"}, description="Deletes a pet - Status Code 404")
    public void testPetpetidDELETEDeletesapet404() {
        setUrlPath("/pet/{petId}");
        replacePathParam("petId", "value");
        sendRequest(Method.DELETE);
        validateExpectedStatus(404);
    }

    @Test(groups={"pet"}, description="Updates a pet in the store with form data - Status Code 405")
    public void testPetpetidPOSTUpdatesapetinthestorewithformdata405() {
        setUrlPath("/pet/{petId}");
        replacePathParam("petId", "value");
        sendRequest(Method.POST);
        validateExpectedStatus(405);
    }

    @Test(groups={"pet"}, description="Find pet by ID - Status Code 200")
    public void testPetpetidGETFindpetbyID200() {
        setUrlPath("/pet/{petId}");
        replacePathParam("petId", "value");
        sendRequest(Method.GET);
        validateExpectedStatus(200);
    }

    @Test(groups={"pet"}, description="Find pet by ID - Status Code 400")
    public void testPetpetidGETFindpetbyID400() {
        setUrlPath("/pet/{petId}");
        replacePathParam("petId", "value");
        sendRequest(Method.GET);
        validateExpectedStatus(400);
    }

    @Test(groups={"pet"}, description="Find pet by ID - Status Code 404")
    public void testPetpetidGETFindpetbyID404() {
        setUrlPath("/pet/{petId}");
        replacePathParam("petId", "value");
        sendRequest(Method.GET);
        validateExpectedStatus(404);
    }

}