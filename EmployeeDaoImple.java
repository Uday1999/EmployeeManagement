package org.example;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImple implements IEmployeeDao {



    /*\
        REGISTRATIONID NOT NULL NUMBER
    NAME                    VARCHAR2(50)
    EMAIL                   VARCHAR2(50)
    CONTACT                 VARCHAR2(50)
    ADDRESS                 VARCHAR2(50)
    CITY                    VARCHAR2(50)
    DEPARTMENT              VARCHAR2(20)

         */


    @Override
    public boolean authenticateEmployee(int empID, String pass) {
        boolean isAuthenticated = false;
        try {
            // Establish connection to the database
            Connection connection = ConnectionDB.getConnection();

            // SQL query to check employee credentials
            String query = "SELECT COUNT(*) FROM EMPSalary WHERE reg=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, empID);
            preparedStatement.setString(2, pass);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the credentials are correct
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count == 1) {
                    isAuthenticated = true;
                }
            }

            // Close all connections
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }


    @Override
    public String insertEmployee(Employee employee) {

        String response = null;
        String sql = "INSERT INTO Employee (reg,name,email,contact,address,city,department) VALUES (EMPLOYEE_SEQUENCE.NextVAL,?,?,?,?,?,?)";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getContact());
            preparedStatement.setString(4, employee.getAddress());
            preparedStatement.setString(5, employee.getCity());
            preparedStatement.setString(6, employee.getDepartment());

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                response = employee.getName() + " 's details saved successfully in the system.";
            }


        } catch (SQLException | IOException e) {
            response = "Employee data not insert.";
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public String updateEmployee(int registrationId, Employee employee) {

        String response = null;

        try (Connection conn = ConnectionDB.getConnection()) {

            String sql = "UPDATE EMPLOYEE SET name=?,email=?,contact=?,address=?,city=?,department=? WHERE reg=" + registrationId;

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getContact());
            preparedStatement.setString(4, employee.getAddress());
            preparedStatement.setString(5, employee.getCity());
            preparedStatement.setString(6, employee.getDepartment());

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                response = employee.getName() + " 's data modified successfully.";
            }


        } catch (SQLException | IOException e) {
            response = "Unable to update Employee!!";
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public String deleteEmployee(int registrationId) {
        String response = null;

        String sqlChild = "DELETE FROM EMPSalary WHERE reg=?";
        String sqlParent = "DELETE FROM Employee WHERE reg=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmtChild = conn.prepareStatement(sqlChild);
             PreparedStatement pstmtParent = conn.prepareStatement(sqlParent)) {

            // Set parameters for prepared statements
            pstmtChild.setInt(1, registrationId);
            pstmtParent.setInt(1, registrationId);

            // Execute updates
            int rowChild = pstmtChild.executeUpdate();
            int rowParent = pstmtParent.executeUpdate();

            // Check if both deletes were successful
            if (rowParent > 0) {
                response = "Data deleted.";
            } else {
                response = "No data deleted.";
            }

        } catch (SQLException | IOException e) {
            response = "Unable to delete data.";
            e.printStackTrace();
        }
        return response;
    }


    @Override
    public List<Employee> getAllEmployee() {

        String sql = "SELECT * FROM EMPLOYEE";
        List<Employee> employees = null;

        try (Connection conn = ConnectionDB.getConnection(); Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            employees = new ArrayList<>();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setReg(resultSet.getInt("reg"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setContact(resultSet.getString("contact"));
                employee.setAddress(resultSet.getString("address"));
                employee.setCity(resultSet.getString("city"));
                employee.setDepartment(resultSet.getString("Department"));

                employees.add(employee);

            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public Employee getEmployeeByRegistrationId(int registrationId) {

        Employee emp = new Employee();

        String sql = "SELECT reg,name,email,contact,address,city,department FROM Employee WHERE reg=" + registrationId;

        try (Connection conn = ConnectionDB.getConnection(); Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                emp.setReg(resultSet.getInt("reg"));
                emp.setName(resultSet.getString("name"));
                emp.setEmail(resultSet.getString("email"));
                emp.setContact(resultSet.getString("contact"));
                emp.setAddress(resultSet.getString("address"));
                emp.setCity(resultSet.getString("city"));
                emp.setDepartment(resultSet.getString("Department"));

            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return emp;
    }

    @Override
    public String insertEMPSalary(EMPSalary empSalary) {
        String response = null;
        String sql = "INSERT INTO EMPSalary (salaryId,reg,salary,BILLABLEINPROJECT,password) VALUES (EMPSALARY_SEQUENCE.NextVAL,?,?,?,?)";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, empSalary.getReg());
            preparedStatement.setDouble(2, empSalary.getSalary());
            preparedStatement.setString(3, empSalary.getBILLABLEINPROJECT());
            preparedStatement.setString(4, empSalary.getPassword());

            int row = preparedStatement.executeUpdate();
            Employee emp = getEmployeeByRegistrationId(empSalary.reg);

            if (row > 0) {
                response = emp.getName() + "'s salary got credited.";
            }

        } catch (SQLException | IOException e) {
            response = "Salary not credited..";
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public String updateEmployee(int registrationId, EMPSalary empSalary) {

        String response = null;

        try (Connection conn = ConnectionDB.getConnection()) {

            String sql = "UPDATE EMPSalary SET reg=?,salary=?,BILLABLEINPROJECT=?,password=? WHERE reg=" + registrationId;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, empSalary.getReg());
            preparedStatement.setDouble(2, empSalary.getSalary());
            preparedStatement.setString(3, empSalary.getBILLABLEINPROJECT());
            preparedStatement.setString(4, empSalary.getPassword());

            int row = preparedStatement.executeUpdate();
            Employee emp = getEmployeeByRegistrationId(registrationId);

            if (row > 0) {
                response = emp.getName() + "'s data have updated successfully.";
            }

        } catch (SQLException | IOException e) {
            response = "Unable to update data";
            e.printStackTrace();
        }

        return response;
    }

//    @Override
//    public String deleteSalaryId(int registrationID) {
//
//        String response = null;
//
//        String sql = "DELETE FROM Employee e1 WHERE e1.reg=? AND EXISTS (SELECT 1 FROM EMPSalary e2 WHERE e2.reg=?)";
//
//        try(Connection conn = ConnectionDB.getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement(sql)){
//
//            preparedStatement.setInt(1,registrationID);
//            preparedStatement.setInt(2,registrationID);
//
//            int row = preparedStatement.executeUpdate();
//
//            if(row > 0){
//                response = registrationID+"data deleted."+row+" effected:" ;
//            }
//
//        }catch(SQLException | IOException e){
//            response="Unable to delete.";
//            e.printStackTrace();
//        }
//
//        return response;
//    }

    @Override
    public void getEMPSalaryBySalaryId(int registrationId) {

        String sql = "SELECT * FROM Employee JOIN EMPSalary ON Employee.reg = EMPSalary.reg WHERE Employee.reg =" + registrationId;

        try (Connection conn = ConnectionDB.getConnection(); Statement pstmt = conn.createStatement()) {


            ResultSet resultSet = pstmt.executeQuery(sql);

            // Printing rows
            while (resultSet.next()) {
                // Print employee information only once
                System.out.print("-------Employee Information-------" + "\n");
                System.out.print("Employee ID: " + resultSet.getInt("reg") + ",\n");
                System.out.print("Name: " + resultSet.getString("name") + ",\n");
                System.out.print("Email: " + resultSet.getString("email") + ",\n");
                System.out.print("Contact: " + resultSet.getString("contact") + ",\n");
                System.out.print("Address: " + resultSet.getString("address") + ",\n");
                System.out.print("City: " + resultSet.getString("city") + ",\n");
                System.out.print("Department: " + resultSet.getString("department") + ",\n");
                // Print salary information
                System.out.print("-------Salary Information-------" + "\n");
                System.out.print("Salary ID: " + resultSet.getInt("salaryId") + ",\n");
                System.out.print("Employee ID: " + resultSet.getInt("reg") + ",\n");
                System.out.print("Salary: " + resultSet.getDouble("salary") + ",\n");
                System.out.print("Billable in Project: " + resultSet.getString("BILLABLEINPROJECT") + ",\n");
                System.out.print("Password : " + resultSet.getString("password"));
                System.out.println();
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

