package university.management.system;

import java.sql.*;

public class Conn {

    private Connection c;
    public Statement s;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///universitymanagementsystem", "root", "codeforinterview");
            s = c.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }
    }

    // Expose connection in case prepared statements are needed
    public Connection getConnection() {
        return c;
    }

    // Close connection safely
    public void close() {
        try {
            if (s != null) s.close();
            if (c != null) c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}