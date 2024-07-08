package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedLanggananPengguna {
    public static void seedLanggananPengguna() throws SQLException {
        Connection connection = Config.getConnection();
        truncateLanggananPengguna();
        connection.createStatement().executeUpdate("INSERT INTO langganan_pengguna (idLangganan, idPengguna, sampaiDengan) VALUES (1, 1, '2021-01-01')");

        System.out.println("Seed Langganan Pengguna berhasil");
        connection.close();

    }

    private static void truncateLanggananPengguna() {
        Connection connection = null;
        try {
            connection = Config.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE langganan_pengguna");
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
