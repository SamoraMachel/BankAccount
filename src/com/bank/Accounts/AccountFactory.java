package com.bank.Accounts;

import com.bank.Persons.Employee;
import com.bank.Persons.Person;

public class AccountFactory {
    public static Object create(AccountType type, Person person) {
        switch (type) {
            case SAVINGS -> {
                return new SavingsAccount(person).saveAccount();
            }
            case FIXED_DEPOSIT -> {
                return new FixedDepositAccount(person).saveAccount();
            }
            case CURRENT -> {
                if(person instanceof Employee){
                    Employee employee = (Employee) person;
                    return new CurrentAccount(employee).saveAccount();
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}