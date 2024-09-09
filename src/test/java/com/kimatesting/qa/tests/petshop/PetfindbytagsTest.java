package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class PetfindbytagsTest extends ApiManager {

    @Test(groups={"pet"}, description="Finds Pets by tags - Status Code 200")
    public void testPetfindbytagsGETFindsPetsbytags200() {
        setUrlPath("/pet/findByTags");
        sendRequest(Method.GET);
        validateExpectedStatus(200);
    }

    @Test(groups={"pet"}, description="Finds Pets by tags - Status Code 400")
    public void testPetfindbytagsGETFindsPetsbytags400() {
        setUrlPath("/pet/findByTags");
        sendRequest(Method.GET);
        validateExpectedStatus(400);
    }

}