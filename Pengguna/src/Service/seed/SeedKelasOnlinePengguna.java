package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedKelasOnlinePengguna {
    public static void SeedKelasOnlinePengguna() {
        try {
            Connection connection = Config.getConnection();
            truncateKelasOnlinePengguna();
            connection.createStatement().executeUpdate("INSERT INTO kelas_online_pengguna (idKelasOnline, idPengguna) VALUES (1, 1)");
            connection.createStatement().executeUpdate("INSERT INTO kelas_online_pengguna (idKelasOnline, idPengguna) VALUES (1, 2)");

            System.out.println("Seed Kelas Online Pengguna berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void truncateKelasOnlinePengguna() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE kelas_online_pengguna");
            connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            connection.close();
    }
    catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
