
import java.sql.*;

public class DB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_auth_db";
    private static final String USER = "root";
    private static final String PASSWORD = "your_mysql_password";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
