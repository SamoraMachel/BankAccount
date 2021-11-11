package com.bank.Persons;

import com.bank.Accounts.Account;
import com.bank.Accounts.CurrentAccount;
import com.bank.Accounts.FixedDepositAccount;
import com.bank.Accounts.SavingsAccount;

import java.util.ArrayList;
import java.util.Objects;

public class Employee implements Person{
    private String firstName = "";
    private String lastName = "";
    private Gender gender = Gender.MALE;
    private long Contact = 0;
    private long salary = 0;
    private ArrayList<Account> accounts = new ArrayList<>();
    private int accountIndex = 0;

    public Employee(String firstName, String lastName, long Contact) {
        this.setName(firstName, lastName);
        this.setContact(Contact);
    }

    public Employee(String firstName, String lastName, long Contact, Gender gender) {
        this(firstName, lastName, Contact);
        this.setGender(gender);
    }

    public Employee setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        return this;
    }

    public Employee setContact(long contact) {
        Contact = contact;
        return this;
    }

    public Employee setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Employee setSalary(long salary) {
        this.salary = salary;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public Gender getGender() {
        return gender;
    }

    public long getContact() {
        return Contact;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    @Override
    public boolean save() throws Exception {
        boolean isSaved = false;
        if(this.firstName.equals("") || this.lastName.equals("") || this.Contact == 0) {
            isSaved = false;
            throw new Exception("Cannot save Employee with empty attributes. Set the name and Contact of the person");
        } else if (isEmployeeSaved()) {
            throw new Exception("Employee is already saved");
        } else {
            this.personList.add(this);
            isSaved = true;
        }
        return isSaved;
    }

    public long getSalary() {
        return salary;
    }

    @Override
    public boolean delete() throws Exception {
        boolean isDeleted = false;
        boolean isEmployeeInList = this.isEmployeeSaved();

        if(!isEmployeeInList) {
            throw new Exception("Could not delete an employee who has not been saved");
        } else {
            this.personList.remove(this);
            isDeleted = true;
        }
        return isDeleted;
    }

    public static boolean delete(Employee e) throws Exception {
        boolean isEmployeeInList = e.isEmployeeSaved();

        if(!isEmployeeInList) {
            throw new Exception("Could not delete an employee who has not been saved");
        } else {
            Person.personList.remove(e);
            return true;
        }
    }

    @Override
    public Class<?> personType() {
        return this.getClass();
    }

    private boolean isEmployeeSaved() {
        for(Person person : this.personList) {
            try {
                if(person.personType() == Class.forName("com.bank.Persons.Employee")) {
                    Employee employee = ((Employee) person);
                    if (employee.id == this.id) {
                        return true;
                    }
                }
            } catch (ClassNotFoundException e) {
                System.out.println("---> Error: " + e.getMessage());
                System.out.println("---> Error: Could not find the class for Employee");
            }

        }
        return false;
    }

    public void useAccount(String accountNumber) throws Exception {
        for(Account account : accounts) {
            if(account.getAccountNumber().equals(accountNumber)) {
                accountIndex = accounts.indexOf(account);
                break;
            } else {
                throw new Exception(String.format("Account (%s) is not available", accountNumber));
            }
        }
    }

    public void useAccount(int index) throws Exception {
        if(accounts.get(index) != null){
            accountIndex = index;
        }
    }

    public void deposit(long amount) throws Exception {
        if (accounts.size() == 0) {
            throw new Exception("Cannot deposit without any Account");
        } else if ( accounts.get(accountIndex) instanceof CurrentAccount) {
            throw  new Exception("Cannot deposit in a Current Account");
        } else if ( accounts.get(accountIndex) instanceof FixedDepositAccount) {
            FixedDepositAccount depositAccount = (FixedDepositAccount) accounts.get(accountIndex);
            depositAccount.deposit(amount);
        } else {
            SavingsAccount savingsAccount = (SavingsAccount) accounts.get(accountIndex);
            savingsAccount.deposit(amount);
        }
    }

    public void withdraw(long amount) throws Exception {
        if (accounts.size() == 0) {
            throw new Exception("Cannot withdraw without any Account");
        } else if ( accounts.get(accountIndex) instanceof CurrentAccount) {
            throw new Exception("Cannot withdraw from a Current Account");
        } else if ( accounts.get(accountIndex) instanceof FixedDepositAccount) {
            FixedDepositAccount depositAccount = (FixedDepositAccount) accounts.get(accountIndex);
            depositAccount.withdraw(amount);
        } else {
            SavingsAccount savingsAccount = (SavingsAccount) accounts.get(accountIndex);
            savingsAccount.withdraw(amount);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getContact() == employee.getContact()
                && firstName.equals(employee.firstName)
                && lastName.equals(employee.lastName)
                && getGender() == employee.getGender();
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, getGender(), getContact());
    }

    @Override
    public String toString() {
        return String.format("Employee<%s %s>", this.firstName, this.lastName);
    }
}
