package java2_Homework_8.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users", "root", "root");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }




}
