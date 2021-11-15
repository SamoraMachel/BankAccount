package com.bank;

import com.bank.Accounts.*;
import com.bank.Persons.Employee;
import com.bank.Persons.Gender;
import com.bank.Persons.Member;
import com.bank.Persons.Person;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("\n--------------- Welcome to Bank ADMIN ---------------");

        AccountFactory.create(AccountType.SAVINGS, new Employee("Samora", "Machel", 724484152, Gender.FEMALE));
        AccountFactory.create(AccountType.FIXED_DEPOSIT, new Member("Hillary", "Johnte", 754431454));


//        Employee emp = new Employee("Samora", "Machel", 72412341);
//        System.out.println(emp);

        new Main().selections();
    }

    public int selections() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Register Employee \n" +
                "2. Register Member\n" +
                "3. List Users \n" +
                "4. Create Account\n" +
                "5. Quit");
        System.out.print("\nYour selection: ");
        Scanner input = new Scanner(System.in);
        int selection = input.nextInt();
        if(selection < 1 || selection > 5) {
            System.out.println(String.format("\n----> Error : %s is not amongst the selection. Pick a valid choice", selection));
            this.selections();
        }
        switch (selection) {
            case 1 : {
                registerEmployee();
                this.selections();
            }
            case 2 : {
                registerMember();
                this.selections();
            }
            case 3 : {
                listUsers();
                this.selections();
            }
            case 4: {
                accountSelection();
                this.selections();
            }
            case 5 : ;{
                break;
            }
        }
        return selection;
    }

    public Employee registerEmployee() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n--- Employee Registration ---");
        System.out.print("Enter First Name : ");
        String firstName = input.next();
        System.out.print("Enter Last Name : ");
        String lastName = input.next();
        System.out.print("Enter Contact : ");
        long contact = input.nextLong();
        System.out.print("Enter Gender (M or F) : ");
        String _gender = input.next();
        Gender gender = Gender.MALE;
        if (_gender == "F" || _gender == "f") {
            gender = Gender.FEMALE;
        }

        Employee employee = new Employee(firstName, lastName, contact, gender);
        try {
            employee.save();
        } catch (Exception e) {
            System.out.println(String.format("\n---> Error : %s", e.getMessage()));
        }
        return employee;
    }

    public Member registerMember() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Member Registration ---");
        System.out.print("Enter First Name : ");
        String firstName = input.next();
        System.out.print("Enter Last Name : ");
        String lastName = input.next();
        System.out.print("Enter Contact : ");
        long contact = input.nextLong();
        System.out.print("Enter Gender (M or F) : ");
        String _gender = input.next();
        Gender gender = Gender.MALE;
        if (_gender == "F" || _gender == "f") {
            gender = Gender.FEMALE;
        }

        Member member = new Member(firstName, lastName, contact, gender);
        try {
            member.save();
        } catch (Exception e) {
            System.out.println(String.format("\n---> Error : %s", e.getMessage()));
        }
        return member;
    }

    public void listUsers() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Name\t\t\t\tContact\t\t\t\tGender\t\t\tType");
        System.out.println("--------------------------------------------------------------------------");
        for(Person persons : Person.personList) {
            if(persons instanceof Member) {
                Member member = (Member) persons;
                System.out.print(member.getName() + "\t\t");
                System.out.print(member.getContact() + "\t\t\t");
                System.out.print(member.getGender().toString()+"\t\t\t");
                System.out.println("Member");
            } else {
                Employee employee = (Employee) persons;
                System.out.print(employee.getName() + "\t\t");
                System.out.print(employee.getContact() + "\t\t\t");
                System.out.print(employee.getGender().toString()+"\t\t\t");
                System.out.println("Employee");
            }
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public void listAccounts() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("User\t\t\t\tAccount Number\t\t\t\t\t\t\t\tAccount Type\t\tCreated");
        System.out.println("------------------------------------------------------------------------------------------------------");
        for(Account account : Account.listAccount()) {
            System.out.print(account.getAccountOwner().getName() + "\t\t");
            System.out.print(account.getAccountNumber() + "\t\t");
            if(account instanceof CurrentAccount) {
                System.out.print("Current Account\t\t");
            } else if (account instanceof FixedDepositAccount) {
                System.out.print("Fixed Deposit \t\t");
            } else if (account instanceof SavingsAccount) {
                System.out.print("Savings Account\t\t");
            }
            System.out.println(account.getCreated().toString());
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    public int accountSelection() {
        System.out.println("\n--- Account Main Menu ---");
        System.out.println("1. Current Account \n" +
                "2. Fixed Deposit Account \n" +
                "3. Savings Account \n" +
                "4. List Accounts\n" +
                "5. Return to Main Menu");
        System.out.print("\nYour Selection : ");
        Scanner input = new Scanner(System.in);
        int selection = input.nextInt();
        if(selection < 1 || selection > 5) {
            System.out.println(String.format("\n----> Error : %s is not amongst the selection. Pick a valid choice ", selection));
            this.selections();
        }

        switch (selection) {
            case 1 : {
                int userIndex = selectUser();
                createAccount(Person.personList.get(userIndex), AccountType.CURRENT);
                this.accountSelection();
            }
            case 2 : {
                int userIndex = selectUser();
                createAccount(Person.personList.get(userIndex), AccountType.FIXED_DEPOSIT);
                this.accountSelection();
            }
            case 3 : {
                int userIndex = selectUser();
                createAccount(Person.personList.get(userIndex), AccountType.SAVINGS);
                this.accountSelection();
            }
            case 4 : {
                this.listAccounts();
                this.accountSelection();
            }
            case 5 : {
                this.selections();
            }
        }
        return selection;
    }

    public Person getPerson(int index) {
        return Person.personList.get(index);
    }

    public void createAccount(Person person, AccountType type) {
        if(person instanceof Employee) {
            Employee emp = (Employee) person;
            AccountFactory.create(type, emp);
        } else if(person instanceof Member) {
            Member mem = (Member) person;
            AccountFactory.create(type, mem);
        }
    }

    public int selectUser() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("ID\t\tName\t\t\t\tContact\t\t\t\tGender\t\t\tType");
        System.out.println("--------------------------------------------------------------------------");
        for(Person persons : Person.personList) {
            System.out.print(String.format("%s\t\t", Person.personList.indexOf(persons) + 1));
            if(persons instanceof Member) {
                Member member = (Member) persons;
                System.out.print(member.getName() + "\t\t");
                System.out.print(member.getContact() + "\t\t\t");
                System.out.print(member.getGender().toString()+"\t\t\t");
                System.out.println("Member");
            } else {
                Employee employee = (Employee) persons;
                System.out.print(employee.getName() + "\t\t");
                System.out.print(employee.getContact() + "\t\t\t");
                System.out.print(employee.getGender().toString()+"\t\t\t");
                System.out.println("Employee");
            }
        }
        System.out.println("\n--------------------------------------------------------------------------");

        Scanner input = new Scanner(System.in);
        System.out.print("\nSelect user : ");
        int userSelection = input.nextInt();
        if(userSelection < 1 || userSelection > Person.personList.size()) {
            System.out.println(String.format("\n----> Error : %s is not amongst the selection. Pick a valid choice\n", userSelection));
            this.selectUser();
        }
        return (userSelection - 1);
    }





}
