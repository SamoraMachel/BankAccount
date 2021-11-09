package com.bank;

import com.bank.Persons.Employee;
import com.bank.Persons.Person;

public class Main {

    public static void main(String[] args) {
        Employee samora = new Employee("Samora", "Machel", 724484152);
        Employee joseph = new Employee("Joseph", "Otieno", 7000222);
        boolean saved = false;
        try {
            samora.save();
        } catch (Exception e) {
            e.printStackTrace();
        }

        samora.setName("Jonte", "Mariba");

        try {
            joseph.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Person person : Person.personList) {
            System.out.println(person);
        }


    }
}
