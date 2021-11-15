package com.bank.Persons;

import com.bank.Accounts.Account;
import com.bank.Accounts.CurrentAccount;
import com.bank.Accounts.FixedDepositAccount;
import com.bank.Accounts.SavingsAccount;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Member implements Person{
    private String id = UUID.randomUUID().toString();
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
            throw new Exception("Cannot withdraw from a Fixed Deposit Account");
        } else if ( accounts.get(accountIndex) instanceof SavingsAccount) {
            SavingsAccount savingsAccount = (SavingsAccount) accounts.get(accountIndex);
            savingsAccount.withdraw(amount);
        }
    }

    public void deposit(long amount, int years) throws Exception {
        if (accounts.size() == 0) {
            throw new Exception("Cannot deposit without any Account");
        } else if ( accounts.get(accountIndex) instanceof FixedDepositAccount) {
            FixedDepositAccount depositAccount = (FixedDepositAccount) accounts.get(accountIndex);
            depositAccount.deposit(amount, years);
        } else {
            SavingsAccount savingsAccount = (SavingsAccount) accounts.get(accountIndex);
            savingsAccount.deposit(amount);
        }
    }

    public void deposit (long amount) throws Exception {
        this.deposit(amount, 0);
    }

    public boolean isMemberSaved() {
        for(Person person : this.personList) {
            if(person.getId().equals(this.id)) {
                return true;
            }
        }
        return false;
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

    @Override
    public String getId() {
        return id;
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
