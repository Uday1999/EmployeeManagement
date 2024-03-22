package org.example;

import java.util.List;

public interface IEmployeeDao {


    public boolean authenticateEmployee(int empID, String pass);
    public String insertEmployee(Employee employee);

    public String updateEmployee(int registrationId, Employee employee);

    public String deleteEmployee(int registrationId);

    public List<Employee> getAllEmployee();

    public Employee getEmployeeByRegistrationId(int registrationId);

    public String insertEMPSalary(EMPSalary empSalary);

    public String updateEmployee(int registrationId, EMPSalary empSalary);

//    public String deleteSalaryId(int registrationId);

     public void getEMPSalaryBySalaryId(int registrationId);


}
