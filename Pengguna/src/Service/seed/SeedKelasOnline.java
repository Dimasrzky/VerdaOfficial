package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedKelasOnline {
    public static void SeedKelasOnline() {
        try {
            Connection connection = Config.getConnection();
            truncateKelasOnline();
//            idPelatihan, judulSeminar, waktuTanggalSeminar, pengajar, link
            connection.createStatement().executeUpdate("INSERT INTO kelas_online (idPelatihan, judulSeminar, waktuTanggalSeminar, pengajar, link) VALUES (1, 'Wortel', '2021-08-01 08:00:00', 'Pak Budi', 'path-to-wortel-cuy.jpg')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_online (idPelatihan, judulSeminar, waktuTanggalSeminar, pengajar, link) VALUES (1, 'Tomat', '2021-08-01 10:00:00', 'Pak Budi', 'path-to-tomat-cuy.jpg')");

            System.out.println("Seed Kelas Online berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void truncateKelasOnline() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE kelas_online");
            connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
