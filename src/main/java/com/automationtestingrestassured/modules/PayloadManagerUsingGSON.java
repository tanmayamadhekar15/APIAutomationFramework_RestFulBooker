package com.automationtestingrestassured.modules;

import com.automationtestingrestassured.payLoad.request.AuthClass;
import com.automationtestingrestassured.payLoad.request.Booking;
import com.automationtestingrestassured.payLoad.request.Bookingdates;
import com.automationtestingrestassured.payLoad.response.BookingResponse;
import com.automationtestingrestassured.payLoad.response.TokenResponse;
import com.automationtestingrestassured.utils.FakerUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

public class PayloadManagerUsingGSON {

    Gson gsonRef;

    public String createBookingPayload() {

        Booking booking = new Booking();
        Faker faker = new Faker();
        booking.setFirstname("Annie");
        booking.setLastname("Benz");
        booking.setTotalprice(faker.random().nextInt(1000));
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        gsonRef=new Gson();
        String bodyPayload=gsonRef.toJson(booking);
        return bodyPayload;
    }

    public BookingResponse bookingResponseJava(String responseString){
        gsonRef = new Gson();
        BookingResponse bookingResponse = gsonRef.fromJson(responseString,BookingResponse.class);
        return bookingResponse;
    }

    public String setAuthPayload(){
        AuthClass ac=new AuthClass();
        ac.setUsername("admin");
        ac.setPassword("password123");
        gsonRef=new Gson();
        String authPayload=gsonRef.toJson(ac);
        return authPayload;
    }

    public String getTokenJSON(String tokenResponse){
        gsonRef=new Gson();
        TokenResponse tr=gsonRef.fromJson(tokenResponse,TokenResponse.class);
        return tr.getToken();
    }
    public Booking getResponseFromJSON(String getResponse){
        gsonRef = new Gson();
        Booking booking = gsonRef.fromJson(getResponse,Booking.class);
        return booking;
    }

    public String putPayload() {
        Booking booking = new Booking();
        booking.setFirstname("James");
        booking.setLastname("Benz");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gsonRef.toJson(booking);
    }

}
