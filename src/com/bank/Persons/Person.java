package com.bank.Persons;

import com.bank.Accounts.Account;

import java.util.ArrayList;
import java.util.UUID;

enum Gender {
    MALE, FEMALE
}

public interface Person {
    String id = UUID.randomUUID().toString();
    ArrayList<Person> personList = new ArrayList<>();

    boolean save() throws Exception;
    static Person search(String id) {
        for(Person person : personList) {
            if(person.id == id)
                return person;
        }
        return null;
    }

    void useAccount(String accountNumber) throws Exception;
    boolean delete() throws Exception;
    <T> Class<?> personType();
}
