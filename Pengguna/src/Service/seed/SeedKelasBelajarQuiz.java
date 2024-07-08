package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedKelasBelajarQuiz {
    public static void SeedKelasBelajarQuiz() {
        try {
            Connection connection = Config.getConnection();
            truncateKelasBelajarQuiz();
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar_quiz (idKelas, orderQuiz, namaQuiz) VALUES (1, 2, 'Quiz Cara Menanam Wortel 1')");
            connection.createStatement().executeUpdate("INSERT INTO kelas_belajar_quiz (idKelas, orderQuiz, namaQuiz) VALUES (1, 4, 'Quiz Cara Menanam Wortel 2')");

            System.out.println("Seed Kelas Belajar Quiz berhasil");
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Seed Kelas Belajar Quiz berhasil");
    }

    private static void truncateKelasBelajarQuiz() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE kelas_belajar_quiz");
            connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
