package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class UserlogoutTest extends ApiManager {

    @Test(groups={"user"}, description="Logs out current logged in user session - Status Code default")
    public void testUserlogoutGETLogsoutcurrentloggedinusersessiondefault() {
        setUrlPath("/user/logout");
        sendRequest(Method.GET);
        // TODO: Check default status code 
    }

}