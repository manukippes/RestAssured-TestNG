package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class PetfindbystatusTest extends ApiManager {

    @Test(groups={"pet"}, description="Finds Pets by status - Status Code 200")
    public void testPetfindbystatusGETFindsPetsbystatus200() {
        setUrlPath("/pet/findByStatus");
        sendRequest(Method.GET);
        validateExpectedStatus(200);
    }

    @Test(groups={"pet"}, description="Finds Pets by status - Status Code 400")
    public void testPetfindbystatusGETFindsPetsbystatus400() {
        setUrlPath("/pet/findByStatus");
        sendRequest(Method.GET);
        validateExpectedStatus(400);
    }

}