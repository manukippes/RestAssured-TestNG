package com.kimatesting.qa.tests.petshop;

import com.kimatesting.qa.ApiManager;
import org.testng.annotations.Test;
import com.kimatesting.qa.enums.Method;
import java.io.File;
public class StoreorderorderidTest extends ApiManager {

    @Test(groups={"store"}, description="Delete purchase order by ID - Status Code 400")
    public void testStoreorderorderidDELETEDeletepurchaseorderbyID400() {
        setUrlPath("/store/order/{orderId}");
        replacePathParam("orderId", "value");
        sendRequest(Method.DELETE);
        validateExpectedStatus(400);
    }

    @Test(groups={"store"}, description="Delete purchase order by ID - Status Code 404")
    public void testStoreorderorderidDELETEDeletepurchaseorderbyID404() {
        setUrlPath("/store/order/{orderId}");
        replacePathParam("orderId", "value");
        sendRequest(Method.DELETE);
        validateExpectedStatus(404);
    }

    @Test(groups={"store"}, description="Find purchase order by ID - Status Code 200")
    public void testStoreorderorderidGETFindpurchaseorderbyID200() {
        setUrlPath("/store/order/{orderId}");
        replacePathParam("orderId", "value");
        sendRequest(Method.GET);
        validateExpectedStatus(200);
    }

    @Test(groups={"store"}, description="Find purchase order by ID - Status Code 400")
    public void testStoreorderorderidGETFindpurchaseorderbyID400() {
        setUrlPath("/store/order/{orderId}");
        replacePathParam("orderId", "value");
        sendRequest(Method.GET);
        validateExpectedStatus(400);
    }

    @Test(groups={"store"}, description="Find purchase order by ID - Status Code 404")
    public void testStoreorderorderidGETFindpurchaseorderbyID404() {
        setUrlPath("/store/order/{orderId}");
        replacePathParam("orderId", "value");
        sendRequest(Method.GET);
        validateExpectedStatus(404);
    }

}