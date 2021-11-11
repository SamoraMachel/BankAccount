package com.bank;

import com.bank.Accounts.*;
import com.bank.Persons.Employee;
import com.bank.Persons.Member;
import com.bank.Persons.Person;

public class Main {

    public static void main(String[] args) {
        Employee samora = new Employee("Samora", "Machel", 724484152);
        Employee joseph = new Employee("Joseph", "Otieno", 7000222);
        Member justus = new Member("Justus", "Adhiambo", 724484152);

//        SavingsAccount userAccount1 = new SavingsAccount(justus);
        FixedDepositAccount userAccount2 = new FixedDepositAccount(joseph);
        CurrentAccount userAccount3 = new CurrentAccount(joseph);

        boolean saved = false;
        try {
            samora.save();
            justus.save();
            samora.deposit(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AccountFactory.create(AccountType.SAVINGS, samora);

        for(Person person : Person.personList) {
            System.out.println(person.toString());
        }
//        System.out.println(userAccount1);
        System.out.println(userAccount2);
        System.out.println(userAccount3);


    }
}
