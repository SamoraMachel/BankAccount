package com.bank.Accounts;

import com.bank.Persons.Employee;
import com.bank.Persons.Member;
import com.bank.Persons.Person;

public class FixedDepositAccount extends Account {
    private int interest = 0;

    public int getInterest() {
        return interest;
    }

    public FixedDepositAccount(Person person) {
        this.setAccountOwner(person);
    }

    public void deposit(long amount, int years) throws Exception {
        if( years == 0) {
            throw new Exception("A specific duration is required while depositing in a fixed account. Measured in years");
        } else if ( years < 0) {
            throw new Exception("A time duration cannot be declared in a negative value");
        } else {
            interest = (int) (getAmount() * 0.05 * years);
            setAmount(getAmount() + amount + getInterest());
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
        return String.format("FixedDepositAccount{ %s }", getAccountOwner());
    }
}

