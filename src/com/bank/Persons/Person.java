package com.bank.Persons;

import java.util.ArrayList;

enum Gender {
    MALE, FEMALE
}

public interface Person {
    static ArrayList<Person> personList = new ArrayList<Person>();

    boolean save() throws Exception;
    boolean delete() throws Exception;
    <T> Class<?> personType();
}
