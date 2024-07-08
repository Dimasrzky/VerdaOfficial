package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedLangganan {
    public static void SeedLangganan() throws SQLException {
        Connection connection = Config.getConnection();
        truncateLangganan();
        connection.createStatement().executeUpdate("INSERT INTO langganan (jenisLangganan, durasiLangganan, benefitLangganan, hargaLangganan) VALUES ('Economy', '3 Bulan', 'Akses ke semua kelas', 150000)");
        connection.createStatement().executeUpdate("INSERT INTO langganan (jenisLangganan, durasiLangganan, benefitLangganan, hargaLangganan) VALUES ('Premium', '1 Tahun', 'Akses ke semua kelas', 756000)");

        System.out.println("Seed Langganan berhasil");
        connection.close();
    }

    private static void truncateLangganan() {
        Connection connection = null;
        try {
            connection = Config.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE langganan");
            connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
