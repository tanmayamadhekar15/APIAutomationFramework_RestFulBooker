package com.automationtestingrestassured.endPoints;

import com.automationtestingrestassured.utils.TestDataPropertyReader;

import java.io.IOException;

public class APIConstants {

    public static String BASE_URL="https://restful-booker.herokuapp.com";

    public static String BASE_URL_usingPropertyFile;
    public static String CREATE_BOOKING_usingPropertyFile;
    public static String UPDATE_BOOKING_usingPropertyFile;

    static {
        try {
            BASE_URL_usingPropertyFile = TestDataPropertyReader.readAKeyFromPropertyFile("baseUri");
            CREATE_BOOKING_usingPropertyFile=TestDataPropertyReader.readAKeyFromPropertyFile("CREATE_BOOKING");
            UPDATE_BOOKING_usingPropertyFile=TestDataPropertyReader.readAKeyFromPropertyFile("UPDATE_BOOKING");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String AUTH_URL="/auth";

    public static String CREATE_BOOKING="/booking";

    public static String UPDATE_BOOKING="/booking";

    public static String DELETE_BOOKING="/booking";
}
