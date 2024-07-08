package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedPelatihan {
    public static void SeedPelatihan() throws SQLException {
        Connection connection = Config.getConnection();
        truncatePelatihan();
        connection.createStatement().executeUpdate("INSERT INTO pelatihan (namaPelatihan) VALUES ('Pertanian')");
        connection.createStatement().executeUpdate("INSERT INTO pelatihan (namaPelatihan) VALUES ('Perkebunan')");

        System.out.println("Seed Pelatihan berhasil");
        connection.close();
    }

    private static void truncatePelatihan() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE pelatihan");
            connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

