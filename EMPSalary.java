package org.example;



/*
SALARYID          NOT NULL NUMBER
REG                        NUMBER
SALARY                     NUMBER(10,2)
BILLABLEINPROJECT          VARCHAR2(50)
 */

public class EMPSalary {

    int salaryId;
    int reg;
    double salary;
    String BILLABLEINPROJECT;
    String password;

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBILLABLEINPROJECT() {
        return BILLABLEINPROJECT;
    }
    public void setBILLABLEINPROJECT(String BILLABLEINPROJECT) {
        this.BILLABLEINPROJECT = BILLABLEINPROJECT;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
