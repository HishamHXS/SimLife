package com.maingame.data;

import net.datafaker.Faker;

public class RealNameFaker {

    private static final Faker faker = new Faker();

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getFemaleFirstName() {
        return faker.name().femaleFirstName();
    }

    public static String getMaleFirstName() {
        return faker.name().malefirstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

}

