package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {


    public static Connection getConnection() throws IOException, SQLException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                "C:\\Users\\UDAY\\Documents\\workspace-spring-tool-suite-4-4.21.0.RELEASE\\JDBCStudentInformation\\src\\main\\java\\org\\example\\jdbc.properties");
        prop.load(fis);

        Connection conn =  DriverManager.getConnection(prop.getProperty("DB_URL"),
                prop.getProperty("DB_USERNAME"),prop.getProperty("DB_PASSWORD"));

        return conn;


    }
}
