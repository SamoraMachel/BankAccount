package com.bank.Accounts;

import com.bank.Persons.Employee;

public class CurrentAccount extends Account {
    public CurrentAccount(Employee employee)  {
        setAccountOwner(employee);
    }

    public void receiveSalary() {
        Employee employee = (Employee) getAccountOwner();
        setAmount(employee.getSalary() + getAmount());
    }

    @Override
    public boolean saveAccount() {
        boolean isSaved = super.saveAccount();
        Employee employee = (Employee) getAccountOwner();
        employee.addAccount(this);
        return isSaved;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("CurrentAccount { user = %s }", this.getAccountOwner());
    }
}
