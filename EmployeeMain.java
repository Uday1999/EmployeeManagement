package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EmployeeMain {

    public static void main(String[] args) {

        IEmployeeDao dao = new EmployeeDaoImple();

        System.out.println("Enter Employee ID :");
        int EmployeeID = Utility.getInt();
        System.out.println("Enter Password :");
        String pass = Utility.getWord();
        Employee emp1 = dao.getEmployeeByRegistrationId(EmployeeID);

        boolean val = dao.authenticateEmployee(EmployeeID, pass);

        if(val ==true)
        {
            System.out.println("LOGIN SUCCESSFULLY..."+ emp1.getName() +" Welcome back");
            showManu(dao);
        }else{
            System.out.println("WRONG PASSWORD LOGIN DENIED...");
        }


    }
    /*
NAME                    VARCHAR2(50)
EMAIL                   VARCHAR2(50)
CONTACT                 VARCHAR2(50)
ADDRESS                 VARCHAR2(50)
CITY                    VARCHAR2(50)
DEPARTMENT              VARCHAR2(20)
     */

    private static void showManu(IEmployeeDao dao) {

        while (true) {
            System.out.println("\n================= WELCOME TO EMPLOYEE MANAGEMENT ================= \n");
            System.out.println("1. Register a new Employee.");
            System.out.println("2. Update any existing Employee's detail.");
            System.out.println("3. Delete any employee by Registration number..");
            System.out.println("4. View All Employees details");
            System.out.println("5. Search any Employee by Registration number.");
            System.out.println("6. Insert salary to Employee.");
            System.out.println("7. Search any Employee details by Registration number.");
            System.out.println("8. Update in Salary-Table");
//            System.out.println("9. Delete any record by Employee Number..");
            System.out.println("0. To Exit, Enter value 0");

            System.out.println("Enter your choice:--------");
            int choice = Utility.getInt();
            Utility.getLine();

            switch (choice) {

                case 1:
                    System.out.println("======= ADDING NEW EMPLOYEE =======");
                    Employee newEmployee = new Employee();

                    System.out.println("Enter Employee Name");
                    newEmployee.setName(Utility.getLine());
                    System.out.println("Enter Employee's Email-Address");
                    newEmployee.setEmail(Utility.getLine());
                    System.out.println("Enter Employee's Contact Number ");
                    newEmployee.setContact(Utility.getLine());
                    System.out.println("Enter Employee's Address");
                    newEmployee.setAddress(Utility.getLine());
                    System.out.println("Enter Employee's City");
                    newEmployee.setCity(Utility.getLine());
                    System.out.println("Enter Employee's Department");
                    newEmployee.setDepartment(Utility.getLine());

                    System.out.println(dao.insertEmployee(newEmployee));
                    break;

                case 2:
                    System.out.println("======= UPDATING AN EMPLOYEE =======");

                    Employee updateEmployee = new Employee();

                    int updateRegistrationNumber;

                    System.out.println("Enter registration ID for the Employee.");
                    updateRegistrationNumber = Utility.getInt();
                    Utility.getLine();

                    System.out.println("Enter Employee name.");
                    updateEmployee.setName(Utility.getLine());
                    System.out.println("Enter Employee email.");
                    updateEmployee.setEmail(Utility.getLine());
                    System.out.println("Enter Employee contact.");
                    updateEmployee.setContact(Utility.getLine());
                    System.out.println("Enter Employee address.");
                    updateEmployee.setAddress(Utility.getLine());
                    System.out.println("Enter Employee city");
                    updateEmployee.setCity(Utility.getLine());
                    System.out.println("Enter Employee department");
                    updateEmployee.setDepartment(Utility.getLine());

                    System.out.println(dao.updateEmployee(updateRegistrationNumber, updateEmployee));
                    break;

                case 3:

                    System.out.println("======= DELETE EMPLOYEE =======");

                    int registrationId;

                    System.out.println("Enter the registration Id.");
                    registrationId = Utility.getInt();

                    System.out.println(dao.deleteEmployee(registrationId));
                    break;

                case 4:

                    System.out.println("======= GET ALL EMPLOYEE DETAILS =======");
                    List<Employee> employees = dao.getAllEmployee();

                    for (Employee e : employees) {
                        System.out.println("Name :" + e.getName());
                        System.out.println("Email-Address :" + e.getEmail());
                        System.out.println("Contact Number :" + e.getContact());
                        System.out.println("Address :" + e.getAddress());
                        System.out.println("City :" + e.getCity());
                        System.out.println("Department :" + e.getDepartment() + "\n");
                    }
                    break;

                case 5:

                    System.out.println("=======  SEARCHING STUDENT BY REGISTRATION NUMBER =======");
                    System.out.println("Enter the registration Id.");

                    int searchRegistrationNumber = Utility.getInt();
                    Utility.getLine();

                    Employee employee = dao.getEmployeeByRegistrationId(searchRegistrationNumber);

                    System.out.println("Student found : ");
                    System.out.println("Registration ID : " + employee.getReg());
                    System.out.println("Name : " + employee.getName());
                    System.out.println("Email : " + employee.getEmail());
                    System.out.println("contact : " + employee.getContact());
                    System.out.println("Address : " + employee.getAddress());
                    System.out.println("City : " + employee.getCity());
                    System.out.println("Department : " + employee.getDepartment());

                    break;

                case 6:

                    System.out.println("======= SALARY CREDITED TO EMPLOYEE =======");
                    EMPSalary empSalary = new EMPSalary();

                    System.out.println("Enter Employee Id :");
                    empSalary.setReg(Utility.getInt());
                    System.out.println("Enter Salary amount : ");
                    empSalary.setSalary(Utility.getDecimal());
                    System.out.println("Enter Project Name : ");
                    empSalary.setBILLABLEINPROJECT(Utility.getWord());
                    System.out.println("Enter Password : ");
                    empSalary.setPassword(Utility.getWord());

                    //System.out.println(empSalary); // For debugging purposes

                    System.out.println(dao.insertEMPSalary(empSalary));
                    break;

                case 7:

                    System.out.println("=======  SEARCHING EMPLOYEE BY REGISTRATION NUMBER =======");
                    System.out.println("Enter the registration Id.");

                    int searchRegistrationID = Utility.getInt();
                    Utility.getLine();

                    dao.getEMPSalaryBySalaryId(searchRegistrationID);
                    break;

                case 8:

                    System.out.println("======= UPDATING AN EMPLOYEE'S SALARY =======");

                    EMPSalary emp = new EMPSalary();

                    int updateRegistrationID;

                    System.out.println("Enter registration ID for the Employee : ");
                    updateRegistrationID = Utility.getInt();
                    Utility.getLine();

                    System.out.println("Enter the Registration-ID : Preferred not to change this value ");
                    emp.setReg(Utility.getInt());
                    System.out.println("Enter the Salary : ");
                    emp.setSalary(Utility.getDecimal());
                    System.out.println("Enter the new Project-Name : ");
                    emp.setBILLABLEINPROJECT(Utility.getWord());
                    System.out.println("Enter the new Password : ");
                    emp.setPassword(Utility.getWord());

                    System.out.println(dao.updateEmployee(updateRegistrationID, emp));
                    break;

                case 0:
                    System.out.println("\nExiting the application... Thank you for your time !!!");
                    System.exit(0);
                default:
                    System.out.println("\\nIncorrect input!!! Please re-enter choice from our menu again !!!");

            }


        }
    }
}