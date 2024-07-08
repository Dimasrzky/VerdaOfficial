package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedJawabanPengguna {
    public static void SeedJawabanPengguna() {
        try {
            Connection connection = Config.getConnection();
            truncateJawabanPengguna();
//            idPengguna, idJawanan
            connection.createStatement().executeUpdate("INSERT INTO jawaban_pengguna (idPengguna, idJawaban) VALUES (1, 1)");

            System.out.println("Seed Jawaban Pengguna berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void truncateJawabanPengguna() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE jawaban_pengguna");
            connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            connection.close();

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
