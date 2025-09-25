package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    //database connection create:
    private DbUtil(){

    }

    private static String url = "jdbc:postgresql://localhost:5432/car_show_room";
    private static String dbUser = "car_show_room";
    private static String dbPassword = "";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,dbUser,dbPassword);
    }

}
