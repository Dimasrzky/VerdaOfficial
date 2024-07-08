package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedPertanyaanQuiz {
    public static void SeedPertanyaanQuiz() {
        try {
            Connection connection = Config.getConnection();
            truncatePertanyaanQuiz();
            connection.createStatement().executeUpdate("INSERT INTO pertanyaan_quiz (idQuiz, pertanyaan) VALUES (1, 'Berapa kedalaman tanah yang ideal untuk menanam biji wortel?')");
            connection.createStatement().executeUpdate("INSERT INTO pertanyaan_quiz (idQuiz, pertanyaan) VALUES (1, 'Berapa lama waktu yang dibutuhkan wortel untuk siap panen setelah ditanam?')");
            connection.createStatement().executeUpdate("INSERT INTO pertanyaan_quiz (idQuiz, pertanyaan) VALUES (1, 'Apa jenis tanah yang paling cocok untuk menanam wortel?')");
            connection.createStatement().executeUpdate("INSERT INTO pertanyaan_quiz (idQuiz, pertanyaan) VALUES (2, 'Apa hal yang harus dihindari saat menanam wortel?')");

            System.out.println("Seed Pertanyaan Quiz berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Seed Pertanyaan Quiz berhasil");
    }

    private static void truncatePertanyaanQuiz() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE pertanyaan_quiz");
            connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
