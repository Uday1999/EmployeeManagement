package org.example;

/*
REGISTRATIONID NOT NULL NUMBER
NAME                    VARCHAR2(50)
EMAIL                   VARCHAR2(50)
CONTACT                 VARCHAR2(50)
ADDRESS                 VARCHAR2(50)
CITY                    VARCHAR2(50)
DEPARTMENT              VARCHAR2(20)

 */
public class Employee {

    int reg;
    String name;
    String email;
    String contact;
    String address;
    String city;
    String Department;

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }
}
