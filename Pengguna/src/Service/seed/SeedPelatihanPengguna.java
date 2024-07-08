package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedPelatihanPengguna {
    public static void SeedPelatihanPengguna() {
        try {
            Connection connection = Config.getConnection();
            truncatePelatihanPengguna();
            connection.createStatement().executeUpdate("INSERT INTO pelatihan_pengguna (idPelatihan, idPengguna, aktifHingga, status) VALUES (1, 1, '08-10-2024', 'pending')");
            connection.createStatement().executeUpdate("INSERT INTO pelatihan_pengguna (idPelatihan, idPengguna, aktifHingga, status) VALUES (1, 2, '08-10-2024', 'pending')");

            System.out.println("Seed Pelatihan Pengguna berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void truncatePelatihanPengguna() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE pelatihan_pengguna");
            connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
