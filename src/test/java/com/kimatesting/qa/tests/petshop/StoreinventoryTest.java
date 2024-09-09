package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class StoreinventoryTest extends ApiManager {

    @Test(groups={"store"}, description="Returns pet inventories by status - Status Code 200")
    public void testStoreinventoryGETReturnspetinventoriesbystatus200() {
        setUrlPath("/store/inventory");
        sendRequest(Method.GET);
        validateExpectedStatus(200);
    }

}