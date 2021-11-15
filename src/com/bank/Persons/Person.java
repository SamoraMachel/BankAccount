package com.bank.Persons;

import com.bank.Accounts.Account;

import java.util.ArrayList;
import java.util.UUID;

public interface Person {
    ArrayList<Person> personList = new ArrayList<>();

    boolean save() throws Exception;

    String getId();
    String getName();
    void useAccount(String accountNumber) throws Exception;
    boolean delete() throws Exception;
    <T> Class<?> personType();
}
