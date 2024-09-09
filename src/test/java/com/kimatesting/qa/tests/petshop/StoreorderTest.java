package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class StoreorderTest extends ApiManager {

    @Test(groups={"store"}, description="Place an order for a pet - Status Code 200")
    public void testStoreorderPOSTPlaceanorderforapet200() {
        setUrlPath("/store/order");
        sendRequest(Method.POST);
        validateExpectedStatus(200);
    }

    @Test(groups={"store"}, description="Place an order for a pet - Status Code 400")
    public void testStoreorderPOSTPlaceanorderforapet400() {
        setUrlPath("/store/order");
        sendRequest(Method.POST);
        validateExpectedStatus(400);
    }

}