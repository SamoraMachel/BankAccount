package com.bank.Accounts;

import com.bank.Persons.Employee;
import com.bank.Persons.Member;
import com.bank.Persons.Person;

public class SavingsAccount extends Account{
    public SavingsAccount(Person person) {
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

    @Override
    public boolean saveAccount() {
        boolean isSaved = super.saveAccount();
        Person user = getAccountOwner();
        if(user instanceof Member) {
            Member member = (Member) user;
            member.addAccount(this);
        } else if(user instanceof Employee) {
            Employee employee = (Employee) user;
            employee.addAccount(this);
        }
        return isSaved;
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
        return String.format("SavingsAccount{ %s }", getAccountOwner());
    }
}

