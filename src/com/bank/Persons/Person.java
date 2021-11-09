package com.bank.Persons;

import java.util.ArrayList;
import java.util.UUID;

enum Gender {
    MALE, FEMALE
}

public interface Person {
    String id = UUID.randomUUID().toString();
    static ArrayList<Person> personList = new ArrayList<Person>();

    boolean save() throws Exception;
    static Person search(String id) {
        for(Person person : personList) {
            if(person.id == id)
                return person;
        }
        return null;
    }
    boolean delete() throws Exception;
    <T> Class<?> personType();
}
