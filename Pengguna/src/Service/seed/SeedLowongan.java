package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedLowongan {
    public static void SeedLowongan() throws SQLException {
        Connection connection = Config.getConnection();
        truncateLowongan();
        connection.createStatement().executeUpdate("INSERT INTO lowongan (posisi, perusahaan, lokasi, gaji, kualifikasi, waktuDiposting, jenisWaktu, jobDesk, tanggungJawab) VALUES ('Software Engineer', 'Google', 'Mountain View, California', 10000000, 'S1 Teknik Informatika', '2021-01-01', 'Full-time', 'Membuat aplikasi', 'Membuat aplikasi')");
        connection.createStatement().executeUpdate("INSERT INTO lowongan (posisi, perusahaan, lokasi, gaji, kualifikasi, waktuDiposting, jenisWaktu, jobDesk, tanggungJawab) VALUES ('Software Engineer', 'Facebook', 'Menlo Park, California', 10000000, 'S1 Teknik Informatika', '2021-01-01', 'Full-time', 'Membuat aplikasi', 'Membuat aplikasi')");
        connection.createStatement().executeUpdate("INSERT INTO lowongan (posisi, perusahaan, lokasi, gaji, kualifikasi, waktuDiposting, jenisWaktu, jobDesk, tanggungJawab) VALUES ('Software Engineer', 'Microsoft', 'Redmond, Washington', 10000000, 'S1 Teknik Informatika', '2021-01-01', 'Full-time', 'Membuat aplikasi', 'Membuat aplikasi')");

        System.out.println("Seed Lowongan berhasil");
        connection.close();
    }

    private static void truncateLowongan() throws SQLException {
        Connection connection = Config.getConnection();
        connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
        connection.createStatement().executeUpdate("TRUNCATE TABLE lowongan");
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

        connection.close();
    }
}
