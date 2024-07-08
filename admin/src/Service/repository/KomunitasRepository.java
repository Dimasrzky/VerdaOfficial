package Service.repository;

import Models.Komunitas;
import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class KomunitasRepository {
    public static ArrayList<Komunitas> getAllKomunitas() {
        ArrayList<Komunitas> komunitas = new ArrayList<>();
        try {
            Connection conn = Config.getConnection();
            var statement = conn.createStatement();
            var result = statement.executeQuery("SELECT * FROM komunitas");
            while (result.next()) {
                Komunitas komunitas1 = new Komunitas(
                        result.getInt("id"),
                        result.getString("nama"),
                        result.getString("kategori"),
                        result.getString("visibility")
                );
                komunitas.add(komunitas1);
            }
            return komunitas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addKomunitas(String nama, String kategori, String visibility) {
        try {
            Connection conn = Config.getConnection();
            var statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO komunitas (nama, kategori, visibility) VALUES ('" + nama + "', '" + kategori + "', '" + visibility + "')");
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteKomunitas(int id) {
        try {
            Connection conn = Config.getConnection();
            var statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM komunitas WHERE id = " + id);
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateKomunitas(int id, String nama, String kategori, String visibility) {
        try {
            Connection conn = Config.getConnection();
            var statement = conn.createStatement();
            statement.executeUpdate("UPDATE komunitas SET nama = '" + nama + "', kategori = '" + kategori + "', visibility = '" + visibility + "' WHERE id = " + id);
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Komunitas getKomunitas(int id) {
        try {
            Connection conn = Config.getConnection();
            var statement = conn.createStatement();
            var result = statement.executeQuery("SELECT * FROM komunitas WHERE id = " + id);
            if (result.next()) {
                Komunitas komunitas = new Komunitas(
                        result.getInt("id"),
                        result.getString("nama"),
                        result.getString("kategori"),
                        result.getString("visibility")
                );
                return komunitas;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updatePublicVisibility(int id) {
        try {
            Connection conn = Config.getConnection();
            var statement = conn.createStatement();
            statement.executeUpdate("UPDATE komunitas SET visibility = 'Public' WHERE id = " + id);
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updatePrivateVisibility(int id) {
        try {
            Connection conn = Config.getConnection();
            var statement = conn.createStatement();
            statement.executeUpdate("UPDATE komunitas SET visibility = 'Private' WHERE id = " + id);
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
