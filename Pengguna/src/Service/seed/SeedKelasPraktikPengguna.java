package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedKelasPraktikPengguna {
    public static void SeedKelasPraktikPengguna() {
        try{
            Connection connection = Config.getConnection();
            truncateKelasPraktikPengguna();
            connection.createStatement().executeUpdate("INSERT INTO kelas_praktik_pengguna (idKelasPraktik, idPengguna, namaLengkap, tumbuhan, linkVideo, fase) VALUES (1, 1, 'Budi', 'Wortel', 'https://www.youtube.com/watch?v=1', 1)");
            connection.createStatement().executeUpdate("INSERT INTO kelas_praktik_pengguna (idKelasPraktik, idPengguna, namaLengkap, tumbuhan, linkVideo, fase) VALUES (1, 2, 'Joko', 'Tomat', 'https://www.youtube.com/watch?v=2', 1)");

            System.out.println("Seed Kelas Praktik Pengguna berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void truncateKelasPraktikPengguna() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE kelas_praktik_pengguna");
            connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            connection.close();
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
