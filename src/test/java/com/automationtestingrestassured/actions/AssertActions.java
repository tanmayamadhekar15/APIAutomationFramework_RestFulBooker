package com.automationtestingrestassured.actions;

import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;

public class AssertActions {
    public void verifyStatusCode(Response response){
        assertEquals(String.valueOf(response.getStatusCode()).startsWith("50"),
                true,
                "Value of status code is " +response.getStatusCode());

       // assertEquals(response.getStatusCode(),true, String.valueOf(response.getStatusCode()));
    }

    public void verifyResponseBody(String actual,String expected,String description){
        assertEquals(actual,expected,description);
    }

    public void verifyResponseBody(float actual,float expected,String description){
        assertEquals(actual,expected,description);
    }
    public void verifyResponseBody(int actual,int expected,String description){
        assertEquals(actual,expected,description);
    }
    public void verifyResponseBody(double actual,double expected,String description){
        assertEquals(actual,expected,description);
    }

}
