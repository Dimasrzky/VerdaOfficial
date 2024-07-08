package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedLowonganPengguna {
    public static void SeedLowonganPengguna() {
        try {
            Connection connection = Config.getConnection();
            truncateLowonganPengguna();
            connection.createStatement().executeUpdate("INSERT INTO lowongan_pengguna (idLowongan, idPengguna, status, namaLengkap, gender, asalDaerah, pendidikanTerakhir, tentangSaya) VALUES (1, 1, 'waiting', 'Rizki','Laki-Laki', 'Bandung', 'S1 Teknik Informatika', 'Saya adalah seorang mahasiswa')");
            connection.createStatement().executeUpdate("INSERT INTO lowongan_pengguna (idLowongan, idPengguna, status, namaLengkap, gender, asalDaerah, pendidikanTerakhir, tentangSaya) VALUES (1, 2, 'accepted', 'Rizka','Perempuan', 'Bandung', 'S1 Teknik Informatika', 'Saya adalah seorang mahasiswa')");
            connection.createStatement().executeUpdate("INSERT INTO lowongan_pengguna (idLowongan, idPengguna, status, namaLengkap, gender, asalDaerah, pendidikanTerakhir, tentangSaya) VALUES (2, 1, 'rejected', 'Rizki','Laki-Laki', 'Bandung', 'S1 Teknik Informatika', 'Saya adalah seorang mahasiswa')");
            System.out.println("Seed Lowongan Pengguna berhasil");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void truncateLowonganPengguna() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate("TRUNCATE TABLE lowongan_pengguna");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
