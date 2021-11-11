package com.bank.Accounts;

import com.bank.Persons.Person;

public class FixedDepositAccount extends Account {

    public FixedDepositAccount(Person person) {
        this.setAccountOwner(person);
    }

    public void deposit(long amount) {
        setAmount(getAmount() + amount);
    }

    public void withdraw(long amount) throws Exception {
        if(amount > getAmount()) {
            throw new Exception("Insufficient funds to perform the operation");
        } else {
            setAmount(getAmount() - amount);
        }
    }

    public long checkBalance() {
        return getAmount();
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
        return String.format("FixedDepositAccount { user = %s }", getAccountOwner());
    }
}

