package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class UserTest extends ApiManager {

    @Test(groups={"user"}, description="Create user - Status Code default")
    public void testUserPOSTCreateuserdefault() {
        setUrlPath("/user");
        sendRequest(Method.POST);
        // TODO: Check default status code 
    }

}