package com.bank.Accounts;

import com.bank.Persons.Employee;
import com.bank.Persons.Person;

public class AccountFactory {
    public static Object create(AccountType type, Person person) {
        boolean isPersonSaved = false;
        for(Person p : Person.personList) {
            if (p == person) {
                isPersonSaved = true;
                break;
            }
        }
        if(!isPersonSaved) {
            try {
                person.save();
            } catch (Exception ignored) {

            }
        }
        switch (type) {
            case SAVINGS -> {
                SavingsAccount savingsAccount = new SavingsAccount(person);
                savingsAccount.saveAccount();
                return savingsAccount;
            }
            case FIXED_DEPOSIT -> {
                FixedDepositAccount fixedDepositAccount = new FixedDepositAccount(person);
                fixedDepositAccount.saveAccount();
                return fixedDepositAccount;
            }
            case CURRENT -> {
                if(person instanceof Employee){
                    Employee employee = (Employee) person;
                    CurrentAccount currentAccount = new CurrentAccount(employee);
                    currentAccount.saveAccount();
                    return currentAccount;
                } else {
                    System.out.println(String.format("---> Error : Cannot open a Current Account for a non Employee : %s", person));
                    return null;
                }
            }
        }

        return null;
    }
}