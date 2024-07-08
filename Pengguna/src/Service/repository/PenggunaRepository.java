package Service.repository;

import Models.Pengguna;
import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class PenggunaRepository {
    public static Pengguna loginDB(String email, String password) throws SQLException {
        Connection connection = Config.getConnection();
        try {
            var result = connection.createStatement().executeQuery("SELECT * FROM pengguna WHERE email = '" + email + "' AND password = '" + password + "'");
            if (result.next()) {
                Pengguna newUser =  new Pengguna(result.getString("namaPengguna"), result.getString("nomorTelepon"), result.getString("jenisKelamin"), result.getString("tanggalLahir"), result.getString("profesi"), result.getString("provinsi"), result.getString("kota"), result.getString("email"), result.getString("password"));
                newUser.setId(result.getInt("id"));
                return newUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
    }
        return null;
    }

    public static boolean registerDB(String username, String email, String password){
        Connection connection = null;
        try {
            connection = Config.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.createStatement().executeUpdate("INSERT INTO pengguna (namaPengguna, email, password) VALUES ('" + username + "', '" + email + "', '" + password + "')");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
