package com.automationtestingrestassured.utils;

import com.github.javafaker.Faker;

public class FakerUtil {
    public static Faker faker;

    public static String getfirstName(){
        faker=new Faker();
        String firstName=faker.name().firstName();
        return firstName;
    }

}
