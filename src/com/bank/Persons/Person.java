package com.bank.Persons;

import com.bank.Accounts.Account;

import java.util.ArrayList;
import java.util.UUID;

enum Gender {
    MALE, FEMALE
}

public interface Person {
    ArrayList<Person> personList = new ArrayList<>();

    boolean save() throws Exception;

    String getId();
    void useAccount(String accountNumber) throws Exception;
    boolean delete() throws Exception;
    <T> Class<?> personType();
}
