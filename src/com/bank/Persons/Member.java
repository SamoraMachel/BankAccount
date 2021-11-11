package com.bank.Persons;

import com.bank.Accounts.Account;
import com.bank.Accounts.CurrentAccount;
import com.bank.Accounts.FixedDepositAccount;
import com.bank.Accounts.SavingsAccount;

import java.util.ArrayList;
import java.util.Objects;

public class Member implements Person{
    private String firstName;
    private String lastName;
    private long contact;
    private Gender gender = Gender.MALE;
    private ArrayList<Account> accounts = new ArrayList<>();
    private int accountIndex = 0;

    public Member(String firstName, String lastName, long contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
    }

    public Member(String firstName, String lastName, long contact, Gender gender) {
        this(firstName, lastName, contact);
        this.gender = gender;
    }

    public Member setAccount(Account account) throws Exception {
        if(account instanceof CurrentAccount) {
            throw new Exception("Could not create a Current Account since the user is not employed");
        } else {
            account.setAccountOwner(this);
            account.saveAccount();
            accounts.add(account);
        }
        return this;
    }

    public Member setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        return this;
    }

    public Member setContact(long contact) {
        this.contact = contact;
        return this;
    }

    public Member setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public String getName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public long getContact() {
        return contact;
    }

    public Gender getGender() {
        return gender;
    }

    public void withdraw(long amount) throws Exception {
        if (accounts.size() == 0) {
            throw new Exception("Cannot withdraw without any Account");
        } else if ( accounts.get(accountIndex) instanceof FixedDepositAccount) {
            FixedDepositAccount depositAccount = (FixedDepositAccount) accounts.get(accountIndex);
            depositAccount.withdraw(amount);
        } else {
            SavingsAccount savingsAccount = (SavingsAccount) accounts.get(accountIndex);
            savingsAccount.withdraw(amount);
        }
    }

    public void deposit(long amount) throws Exception {
        if (accounts.size() == 0) {
            throw new Exception("Cannot deposit without any Account");
        } else if ( accounts.get(accountIndex) instanceof FixedDepositAccount) {
            FixedDepositAccount depositAccount = (FixedDepositAccount) accounts.get(accountIndex);
            depositAccount.deposit(amount);
        } else {
            SavingsAccount savingsAccount = (SavingsAccount) accounts.get(accountIndex);
            savingsAccount.deposit(amount);
        }
    }



    @Override
    public boolean save() throws Exception {
        if(this.firstName.equals("") || this.lastName.equals("") || this.contact == 0) {
            throw new Exception("Cannot save Member with empty attributes. Set the name and Contact of the person");
        } else if (isMemberSaved()) {
            throw new Exception("Member is already saved");
        } else {
            this.personList.add(this);
            return true;
        }
    }

    private boolean isMemberSaved() {
        for(Person person : this.personList) {
            try {
                if(person.personType() == Class.forName("com.bank.Persons.Member")) {
                    Member member= ((Member) person);
                    if (member.id == this.id) {
                        return true;
                    }
                }
            } catch (ClassNotFoundException e) {
                System.out.println("---> Error: " + e.getMessage());
                System.out.println("---> Error: Could not find the class for Member");
            }
        }
        return false;
    }

    @Override
    public boolean delete() throws Exception {
        boolean isDeleted = false;
        boolean isMemberInList = this.isMemberSaved();

        if(!isMemberInList) {
            throw new Exception("Could not delete a Member who has not been saved");
        } else {
            this.personList.remove(this);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public <T> Class<?> personType() {
        return this.getClass();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return  contact == member.contact && firstName.equals(member.firstName)
                && lastName.equals(member.lastName) && gender == member.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, contact, gender);
    }

    @Override
    public String toString() {
        return String.format("Member<%s %s>", this.firstName, this.lastName);
    }
}
