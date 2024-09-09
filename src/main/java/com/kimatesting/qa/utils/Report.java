package com.kimatesting.qa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {
    public static void printRequestLogInReport(QueryableRequestSpecification queryableRequestSpecification) throws JsonProcessingException {
        HashMap<String, Object> requestMap = new HashMap<>();
        requestMap.put("URL ", queryableRequestSpecification.getURI());
        requestMap.put("METHOD ", queryableRequestSpecification.getMethod());
        requestMap.put("HEADERS", addHeadersToReport(queryableRequestSpecification.getHeaders().asList()));
        if(!queryableRequestSpecification.getFormParams().isEmpty()) requestMap.put("FORM-PARAMS ", queryableRequestSpecification.getFormParams().toString());
        if(!queryableRequestSpecification.getQueryParams().isEmpty()) requestMap.put("QUERY-PARAMS ", queryableRequestSpecification.getQueryParams().toString());
        if(!queryableRequestSpecification.getRequestParams().isEmpty()) requestMap.put("REQUEST-PARAMS ", queryableRequestSpecification.getRequestParams().toString());
        if(!queryableRequestSpecification.getPathParams().isEmpty()) requestMap.put("PATHPARAMS ", queryableRequestSpecification.getPathParams().toString());
        if(queryableRequestSpecification.getBody() != null) requestMap.put("BODY ", queryableRequestSpecification.getBody().toString());
        String curl = String.format("curl -X %1$s '%2$s' -d '%3$s' %4$s",queryableRequestSpecification.getMethod(),queryableRequestSpecification.getURI(), getCurlBody(queryableRequestSpecification.getBody()), getCurlHeader(queryableRequestSpecification.getHeaders().asList()));

        ExtentReportHelper.addInfoAsString(String.format("%1$s ENDPOINT: %2$s",queryableRequestSpecification.getMethod(),queryableRequestSpecification.getBasePath()));
        ExtentReportHelper.addLabel("REQUEST");
        ObjectMapper objectMapper = new ObjectMapper();
        ExtentReportHelper.addInfoAsJson(objectMapper.writeValueAsString(requestMap));
        ExtentReportHelper.addInfoAsString(curl);
    }

    private static String getCurlHeader(List headersCurl) {
        String headerString = "";
        for (int i = 0; i < headersCurl.size(); i++) {
            String header = headersCurl.get(i).toString().replaceFirst("=",":");
            headerString = headerString +" -H " +"'"+header+"'";
        }
        headerString = headerString.replace("[","");
        headerString = headerString.replace("]","");
        return headerString;
    }

    private static String getCurlBody(String bodyCurl) {
        String body= "";
        if(bodyCurl != null) return bodyCurl;
        return body;
    }

    private static Map<String, String> addHeadersToReport(List<Header> headers) {
        Map<String, String> headersMap = new HashMap<>();
        for (Header header : headers) {
            headersMap.put(header.getName(), header.getValue());
        }
        return headersMap;
    }


    public static void printResponseLogInReport(Response response) throws JsonProcessingException {
        HashMap<String, Object> responseMap = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        ExtentReportHelper.addLabel("RESPONSE");
        responseMap.put("STATUS ", response.getStatusCode());
        responseMap.put("HEADERS ", addHeadersToReport(response.getHeaders().asList()));
        responseMap.put("BODY ", response.getBody().print());
        ExtentReportHelper.addInfoAsJson(objectMapper.writeValueAsString(responseMap));
    }
}
