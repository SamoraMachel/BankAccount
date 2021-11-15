package com.bank.Accounts;

import com.bank.Persons.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public abstract class Account {
    private static final ArrayList<Account> accountList = new ArrayList<Account>();

    private final String accountNumber = UUID.randomUUID().toString();
    private Person accountOwner = null;
    private long amount = 0;
    private LocalDate created = LocalDate.now();

    public Account setAmount(long amount) {
        this.amount = amount;
        return this;
    }

    public Account setAccountOwner(Person accountOwner) {
        this.accountOwner = accountOwner;
        return this;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Person getAccountOwner() {
        return accountOwner;
    }

    public long getAmount() {
        return amount;
    }

    public LocalDate getCreated() {
        return created;
    }


    public Class<?> accountType(){
        return this.getClass();
    }

    public boolean  saveAccount() {
        return accountList.add(this);
    }

    public boolean deleteAccount(){
        boolean isAccountInList = this.isAccountSaved();
        if(isAccountInList) {
            accountList.remove(this);
            return true;
        }
        return false;
    }

    public static boolean deleteAccount(String id) throws Exception {
        for(Account account : accountList) {
            if(account.accountNumber == id) {
                accountList.remove(account);
                return true;
            }
        }
        throw new Exception(String.format("No account with ID -> $s", id));
    }

    public static ArrayList<Account> listAccount() {
        return accountList;
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
