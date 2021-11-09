package com.bank.Persons;

import java.util.Random;
import java.util.UUID;

enum Gender {
    MALE, FEMALE
}

interface Person {
    String id = UUID.randomUUID().toString();
    String firstName = "";
    String lastName = "";
    Gender gender = Gender.MALE;
    Number Contact = 0;

    boolean save();
    boolean update();
    boolean delete();
}
