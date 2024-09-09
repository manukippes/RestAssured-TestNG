package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class PetTest extends ApiManager {

    @Test(groups={"pet"}, description="Add a new pet to the store - Status Code 405")
    public void testPetPOSTAddanewpettothestore405() {
        setUrlPath("/pet");
        sendRequest(Method.POST);
        validateExpectedStatus(405);
    }

    @Test(groups={"pet"}, description="Update an existing pet - Status Code 400")
    public void testPetPUTUpdateanexistingpet400() {
        setUrlPath("/pet");
        sendRequest(Method.PUT);
        validateExpectedStatus(400);
    }

    @Test(groups={"pet"}, description="Update an existing pet - Status Code 404")
    public void testPetPUTUpdateanexistingpet404() {
        setUrlPath("/pet");
        sendRequest(Method.PUT);
        validateExpectedStatus(404);
    }

    @Test(groups={"pet"}, description="Update an existing pet - Status Code 405")
    public void testPetPUTUpdateanexistingpet405() {
        setUrlPath("/pet");
        sendRequest(Method.PUT);
        validateExpectedStatus(405);
    }

}