package Service.repository;

import Models.Admin;
import Models.PelatihanPengguna;
import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminRepository {
    public static Admin loginAdmin(String email, String password){
        try {
            Connection connection = Config.getConnection();

            var preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE email = ? AND password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            var result = preparedStatement.executeQuery();
            if (result.next()) {
                return new Admin(
                        result.getInt("id"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("username"),
                        result.getString("profile_picture")
                );
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean registerDB(String username, String email, String password, String profile_picture) {
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO admin (email, password, username, profile_picture) VALUES ('" + email + "', '" + password + "', '" + username + "', '" + profile_picture + "')");
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
