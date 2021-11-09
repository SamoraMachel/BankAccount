package com.bank.Accounts;

import com.bank.Persons.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public abstract class Account {
    static ArrayList<Account> accountList = new ArrayList<Account>();

    private String accountNumber = UUID.randomUUID().toString();
    private Person accountOwner = null;
    private int amount = 0;
    private LocalDate created = LocalDate.now();

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Person getAccountOwner() {
        return accountOwner;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getCreated() {
        return created;
    }

    public Account(Person person) {
        this.accountOwner = person;
    }

    Class<?> accountType(){
        return this.getClass();
    }

    boolean saveAccount() {
        return accountList.add(this);
    }
    boolean deleteAccount(){
        boolean isAccountInList = this.isAccountSaved();
        if(isAccountInList) {
            accountList.remove(this);
            return true;
        }
        return false;
    }

    private boolean isAccountSaved() {
        for(Account account : accountList) {
            if (account == this) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return accountNumber.equals(account.accountNumber) && created.equals(account.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, created);
    }
}
