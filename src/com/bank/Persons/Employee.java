package com.bank.Persons;

import java.util.Objects;
import java.util.UUID;

public class Employee implements Person{
    private String firstName = "";
    private String lastName = "";
    private Gender gender = Gender.MALE;
    private long Contact = 0;

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
                System.out.println("--> Error: " + e.getMessage());
                System.out.println("--> Error: Could not find the class for Employee");
            }

        }
        return false;
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
