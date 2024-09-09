package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class UserloginTest extends ApiManager {

    @Test(groups={"user"}, description="Logs user into the system - Status Code 200")
    public void testUserloginGETLogsuserintothesystem200() {
        setUrlPath("/user/login");
        sendRequest(Method.GET);
        validateExpectedStatus(200);
    }

    @Test(groups={"user"}, description="Logs user into the system - Status Code 400")
    public void testUserloginGETLogsuserintothesystem400() {
        setUrlPath("/user/login");
        sendRequest(Method.GET);
        validateExpectedStatus(400);
    }

}