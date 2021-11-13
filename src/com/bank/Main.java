package com.bank;

import com.bank.Accounts.*;
import com.bank.Persons.Employee;
import com.bank.Persons.Member;
import com.bank.Persons.Person;

public class Main {

    public static void main(String[] args) {
        AccountFactory.create(AccountType.SAVINGS, new Employee("Samora", "Machel", 724484152));
        AccountFactory.create(AccountType.FIXED_DEPOSIT, new Employee("Joseph", "Otieno", 7000222));
        AccountFactory.create(AccountType.CURRENT, new Member("Justus", "Adhiambo", 724484152));
        AccountFactory.create(AccountType.SAVINGS, new Member("William", "Waweru", 70023412));

        System.out.println(Account.listAccount());

        System.out.println(Person.personList.size());
        for(Person person : Person.personList) {
            System.out.println(person.toString());
        }

    }
}
