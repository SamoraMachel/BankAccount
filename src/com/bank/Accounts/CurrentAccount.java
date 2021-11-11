package com.bank.Accounts;

import com.bank.Persons.Employee;
import com.bank.Persons.Person;

public class CurrentAccount extends Account {
    public CurrentAccount(Person person) throws Exception {
        if (!(person instanceof Employee)) {
            throw new Exception("Could not create a current account for a user who is not employed");
        } else {
            this.setAccountOwner(person);
        }
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
        return String.format("CurrentAccount { user = %s }", this.getAccountOwner());
    }
}
