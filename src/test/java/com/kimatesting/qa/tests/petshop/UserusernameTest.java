package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class UserusernameTest extends ApiManager {

    @Test(groups={"user"}, description="Delete user - Status Code 400")
    public void testUserusernameDELETEDeleteuser400() {
        setUrlPath("/user/{username}");
        replacePathParam("username", "value");
        sendRequest(Method.DELETE);
        validateExpectedStatus(400);
    }

    @Test(groups={"user"}, description="Delete user - Status Code 404")
    public void testUserusernameDELETEDeleteuser404() {
        setUrlPath("/user/{username}");
        replacePathParam("username", "value");
        sendRequest(Method.DELETE);
        validateExpectedStatus(404);
    }

    @Test(groups={"user"}, description="Get user by user name - Status Code 200")
    public void testUserusernameGETGetuserbyusername200() {
        setUrlPath("/user/{username}");
        replacePathParam("username", "value");
        sendRequest(Method.GET);
        validateExpectedStatus(200);
    }

    @Test(groups={"user"}, description="Get user by user name - Status Code 400")
    public void testUserusernameGETGetuserbyusername400() {
        setUrlPath("/user/{username}");
        replacePathParam("username", "value");
        sendRequest(Method.GET);
        validateExpectedStatus(400);
    }

    @Test(groups={"user"}, description="Get user by user name - Status Code 404")
    public void testUserusernameGETGetuserbyusername404() {
        setUrlPath("/user/{username}");
        replacePathParam("username", "value");
        sendRequest(Method.GET);
        validateExpectedStatus(404);
    }

    @Test(groups={"user"}, description="Updated user - Status Code 400")
    public void testUserusernamePUTUpdateduser400() {
        setUrlPath("/user/{username}");
        replacePathParam("username", "value");
        sendRequest(Method.PUT);
        validateExpectedStatus(400);
    }

    @Test(groups={"user"}, description="Updated user - Status Code 404")
    public void testUserusernamePUTUpdateduser404() {
        setUrlPath("/user/{username}");
        replacePathParam("username", "value");
        sendRequest(Method.PUT);
        validateExpectedStatus(404);
    }

}