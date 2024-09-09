package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class UsercreatewithlistTest extends ApiManager {

    @Test(groups={"user"}, description="Creates list of users with given input array - Status Code default")
    public void testUsercreatewithlistPOSTCreateslistofuserswithgiveninputarraydefault() {
        setUrlPath("/user/createWithList");
        sendRequest(Method.POST);
        // TODO: Check default status code 
    }

}