package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedPengguna {
    public static void SeedPengguna() throws SQLException {
        Connection connection = Config.getConnection();
        truncatePengguna();
        connection.createStatement().executeUpdate("INSERT INTO pengguna (namaPengguna, nomorTelepon, jenisKelamin, tanggalLahir, profesi, provinsi, kota, email, password) VALUES ('Rizki', '08123456789', 'Laki-laki', '2000-01-01', 'Mahasiswa', 'Jawa Barat', 'Bandung', 'rizki@tes123.com', '123456')");
        connection.createStatement().executeUpdate("INSERT INTO pengguna (namaPengguna, nomorTelepon, jenisKelamin, tanggalLahir, profesi, provinsi, kota, email, password) VALUES ('Rizka', '08123456789', 'Perempuan', '2000-01-01', 'Mahasiswa', 'Jawa Barat', 'Bandung', 'rizka@11.x', '123456')");

        System.out.println("Seed Pengguna berhasil");
        connection.close();
    }

    private static void truncatePengguna() throws SQLException {
        Connection connection = Config.getConnection();
        connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
        connection.createStatement().executeUpdate("TRUNCATE TABLE pengguna");
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
        connection.close();
    }
}
