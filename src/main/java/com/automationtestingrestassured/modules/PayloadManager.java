package com.automationtestingrestassured.modules;

import com.automationtestingrestassured.payLoad.request.AuthClass;
import com.automationtestingrestassured.payLoad.request.Booking;
import com.automationtestingrestassured.payLoad.request.Bookingdates;
import com.automationtestingrestassured.payLoad.response.BookingResponse;
import com.automationtestingrestassured.utils.FakerUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayloadManager {

    //Java object to JSONString - Serialization using Jackson
    ObjectMapper objectMapper;

    public String createPayload() throws JsonProcessingException {
        objectMapper=new ObjectMapper();

        Booking booking = new Booking();
        booking.setFirstname(FakerUtil.getfirstName());
        booking.setLastname("Benz");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-10");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
       String jackson_payload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return jackson_payload;
    }

    public BookingResponse JSONToObject(String jsonString) throws JsonProcessingException {
        objectMapper=new ObjectMapper();
        BookingResponse bookingResponse=objectMapper.readValue(jsonString,BookingResponse.class);
        return bookingResponse;
    }

    public Booking JSONToObjectPut(String jsonString) throws JsonProcessingException {
        objectMapper=new ObjectMapper();
        Booking booking=objectMapper.readValue(jsonString,Booking.class);
        return booking;
    }
    public String createPayloadNegative() throws JsonProcessingException {
        objectMapper=new ObjectMapper();

        Booking booking = new Booking();
        booking.setFirstname(FakerUtil.getfirstName());
        booking.setLastname("lastname");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-10");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        String jackson_payload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return jackson_payload;
    }

    public String setToken() throws JsonProcessingException {
        objectMapper=new ObjectMapper();
        AuthClass ac=new AuthClass();
        ac.setUsername("admin");
        ac.setPassword("password123");
        return  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ac);
    }

    public String updatePayload() throws JsonProcessingException {
        objectMapper=new ObjectMapper();

        Booking booking = new Booking();
        booking.setFirstname("Annie");
        booking.setLastname("Antony");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-10");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        String jackson_payload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return jackson_payload;
    }
}
