package com.bank.Accounts;

import com.bank.Persons.Person;

public class SavingsAccount extends Account{
    public SavingsAccount(Person person) {
        super(person);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("SavingsAccount{%s}");
    }
}
