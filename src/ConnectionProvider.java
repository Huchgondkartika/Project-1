

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

    private static Connection con;

    public static Connection getCon() {
        try {
            if (con == null || con.isClosed()) {
                // ✅ Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // ✅ Establish connection to the 'lms' database
                con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lms",  // Database name = lms
                    "root",                             // Username
                    "K@rtika9008"                       // Password
                );

                System.out.println("✅ Database Connected Successfully!");
            }
        } catch (Exception e) {
            System.out.println("❌ Database Connection Failed!");
            e.printStackTrace();
        }
        return con;
    }
}
