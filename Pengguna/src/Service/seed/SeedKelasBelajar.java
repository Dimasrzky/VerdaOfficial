package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedKelasBelajar {
    public static void SeedKelasBelajar() {
        try {
            Connection connection = Config.getConnection();
            truncateKelasBelajar();

            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Wortel', '/Styles/Image/icon%20wortel.png')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Jagung', '/Styles/Image/icon%20jagung.png')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Cabai', '/Styles/Image/icon%20cabai.png')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Bawang', '/Styles/Image/icon%20bawang.png')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Kol', '/Styles/Image/icon%20kol.png')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Labu', '/Styles/Image/icon%20labu.png')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Kentang', '/Styles/Image/icon%20kentang.png')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Tomat', '/Styles/Image/icon%20tomat.png')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Terong', '/Styles/Image/icon%20terong.png')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Kacang', '/Styles/Image/icon%20kacang.png')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Padi', '/Styles/Image/icon%20padi.png')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Bayam', '/Styles/Image/icon%20bayam.png')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar (idPelatihan, namaKelas, gambarKelas) VALUES (1, 'Gandum', '/Styles/Image/icon%20gandum.png')");

            System.out.println("Seed Kelas Belajar berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void truncateKelasBelajar() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE kelas_belajar");
            connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
