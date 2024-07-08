package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedKelasBelajarVideo {
    public static void SeedKelasBelajarVideo() {
        try {
            Connection connection = Config.getConnection();
            truncateKelasBelajarVideo();
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar_video (idKelas, judulVideo, orderVideo, linkVideo) VALUES (1, 'Belajar dasar cara menanam wortel + persiapan', 1, '/Styles/Videos/nanamwortel.mp4')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar_video (idKelas, judulVideo, orderVideo, linkVideo) VALUES (1, 'Hal hal yang harus dihindari saat menanam wortel', 3, '/Styles/Videos/nanamwortel.mp4')");

            System.out.println("Seed Kelas Belajar Video berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Seed Kelas Belajar Video berhasil");
    }

    private static void truncateKelasBelajarVideo() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE kelas_belajar_video");
            connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
