package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class PetpetiduploadimageTest extends ApiManager {

    @Test(groups={"pet"}, description="uploads an image - Status Code 200")
    public void testPetpetiduploadimagePOSTuploadsanimage200() {
        setUrlPath("/pet/{petId}/uploadImage");
        replacePathParam("petId", "value");
        sendRequest(Method.POST);
        validateExpectedStatus(200);
    }

}