package com.bank.Accounts;

import java.util.ArrayList;

public interface Account {
    ArrayList<Account> accountList = new ArrayList<Account>();

    <T> T accountType();
    boolean saveAccount();
    boolean deleteAccount();
}
