package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Models.CobaModel;

public class Data {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/verda";
    private static final String USER = "root";
    private static final String PASS = "";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading JDBC Driver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("Database connected");
        return DriverManager. getConnection(DB_URL, USER, PASS);
    }

    public static List<CobaModel> getUsers() {
        List<CobaModel> users = new ArrayList<>();
        String query = "SELECT id, nama, nim FROM haikal";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String nim = rs.getString("nim");
                users.add(new CobaModel(id, nama, nim));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving users: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }
}
