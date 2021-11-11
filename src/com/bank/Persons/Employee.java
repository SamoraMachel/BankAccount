package com.bank.Persons;

import com.bank.Accounts.Account;
import com.bank.Accounts.CurrentAccount;
import com.bank.Accounts.FixedDepositAccount;
import com.bank.Accounts.SavingsAccount;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Employee implements Person{
    private final String id = UUID.randomUUID().toString();
    private String firstName = "";
    private String lastName = "";
    private Gender gender = Gender.MALE;
    private long Contact = 0;
    private long salary = 0;
    private final ArrayList<Account> accounts = new ArrayList<>();
    private int accountIndex = 0;
    private int salaryAccountIndex = -1;

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

    public Employee setSalaryAccountIndex(int salaryAccountIndex) {
        this.salaryAccountIndex = salaryAccountIndex;
        return this;
    }

    @Override
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

    public long getSalary() {
        return salary;
    }

    @Override
    public boolean save() throws Exception {
        if(this.firstName.equals("") || this.lastName.equals("") || this.Contact == 0) {
            throw new Exception("Cannot save Employee with empty attributes. Set the name and Contact of the person");
        } else if (isEmployeeSaved()) {
            throw new Exception("Employee is already saved");
        } else {
            this.personList.add(this);
            return true;
        }
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

    public void paySalary() throws Exception {
        if(accounts.size() == 0) {
            throw new Exception("No account available");
        } else if (salary == 0) {
            throw new Exception("Salary has not been set");
        } else {
            for (Account account : accounts) {
                if (account instanceof CurrentAccount) {
                    CurrentAccount currentAccount = (CurrentAccount) account;
                    currentAccount.receiveSalary();
                }

            }
        }
    }

    public boolean deleteAccount(String accountNumber) throws Exception {
        for(Account account : accounts) {
            if(account.getAccountNumber().equals(accountNumber)) {
                accounts.remove(account);
                return true;
            }
        }
        throw new Exception(String.format("No account with ID -> $s", id));
    }

    public void addAccount(Account account) {
        accounts.add(account);
        if(account instanceof CurrentAccount) {
            salaryAccountIndex = accounts.indexOf(account);
        }
    }

    @Override
    public Class<?> personType() {
        return this.getClass();
    }

    public boolean isEmployeeSaved() {
        for(Person person : this.personList) {
            if(person.getId().equals(this.id)) {
                return true;
            }
        }
        return false;
    }

    @Override
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
