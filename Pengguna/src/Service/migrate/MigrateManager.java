package Service.migrate;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class MigrateManager {


    public static void migrate() throws SQLException {
        migratePengguna();
        migrateLowongan();
        migrateLowonganPengguna();
        migratePelatihan();
        migratePelatihanPengguna();
        migrateKelasBelajar();
        migrateKelasBelajarVideo();
        migrateKelasBelajarQuiz();
        migratePertanyaanQuiz();
        migrateJawabanQuiz();
        migrateJawabanPengguna();
        migrateKelasOnline();
        migrateKelasOnlinePengguna();
        migrateKelasPraktik();
        migrateKelasPraktikPengguna();
        migrateLangganan();
        migrateLanggananPengguna();
    }

    public static void main(String[] args) throws SQLException {
        dropAllTable();
        migrate();
    }

    public static void migratePengguna() throws SQLException {
        // Migrate Pengguna
        Connection connection = Config.getConnection();
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS pengguna (id INT PRIMARY KEY AUTO_INCREMENT, namaPengguna VARCHAR(255), nomorTelepon VARCHAR(255), jenisKelamin VARCHAR(255), tanggalLahir VARCHAR(255), profesi VARCHAR(255), provinsi VARCHAR(255), kota VARCHAR(255), email VARCHAR(255), password VARCHAR(255)) ");
        System.out.println("Migrate Pengguna berhasil");
        connection.close();
    }

    public static void migrateLowongan() throws SQLException {
        Connection connection = Config.getConnection();
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS lowongan (id INT PRIMARY KEY AUTO_INCREMENT, posisi VARCHAR(255), perusahaan VARCHAR(255), lokasi VARCHAR(255), gaji INT, kualifikasi VARCHAR(255), waktuDiposting VARCHAR(255), jenisWaktu VARCHAR(255), jobDesk VARCHAR(3000), tanggungJawab VARCHAR(3000))");
        System.out.println("Migrate Lowongan berhasil");
        connection.close();

    }

    public static void migrateLowonganPengguna() throws SQLException {
        Connection connection = Config.getConnection();
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS lowongan_pengguna (id INT PRIMARY KEY AUTO_INCREMENT, idLowongan INT, idPengguna INT, status VARCHAR(255),namaLengkap VARCHAR(255), asalDaerah VARCHAR(255), gender VARCHAR(255), pendidikanTerakhir VARCHAR(255), tentangSaya VARCHAR(3000), FOREIGN KEY (idLowongan) REFERENCES lowongan(id) ON DELETE CASCADE, FOREIGN KEY (idPengguna) REFERENCES pengguna(id) ON DELETE CASCADE)");

        System.out.println("Migrate Lowongan Pengguna berhasil");
        connection.close();
    }

    public static void migratePelatihan(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS pelatihan (id INT PRIMARY KEY AUTO_INCREMENT, namaPelatihan VARCHAR(255))");

            System.out.println("Migrate Pelatihan berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void migratePelatihanPengguna(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS pelatihan_pengguna (id INT PRIMARY KEY AUTO_INCREMENT, idPelatihan INT, idPengguna INT, aktifHingga VARCHAR(255), status VARCHAR(255), FOREIGN KEY (idPelatihan) REFERENCES pelatihan(id) ON DELETE CASCADE, FOREIGN KEY (idPengguna) REFERENCES pengguna(id) ON DELETE CASCADE)");

            System.out.println("Migrate Pelatihan Pengguna berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void migrateKelasBelajar(){
        try {
            Connection connection = Config.getConnection();
//            have foreign key to pelatihan
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS kelas_belajar (id INT PRIMARY KEY AUTO_INCREMENT, idPelatihan INT, namaKelas VARCHAR(255), gambarKelas VARCHAR(255), FOREIGN KEY (idPelatihan) REFERENCES pelatihan(id) ON DELETE CASCADE)");

            System.out.println("Migrate Kelas Belajar berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void migrateKelasBelajarVideo() throws SQLException {
//        string judulVideo, link video
        Connection connection = Config.getConnection();
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS kelas_belajar_video (id INT PRIMARY KEY AUTO_INCREMENT, idKelas INT, judulVideo VARCHAR(255), orderVideo INT, linkVideo VARCHAR(255), FOREIGN KEY (idKelas) REFERENCES kelas_belajar(id) ON DELETE CASCADE)");
        System.out.println("Migrate Kelas Belajar Video berhasil");
        connection.close();
    }

    public static void migrateKelasBelajarQuiz() throws SQLException {
        Connection connection = Config.getConnection();
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS kelas_belajar_quiz (id INT PRIMARY KEY AUTO_INCREMENT, idKelas INT, orderQuiz INT, namaQuiz VARCHAR(255), FOREIGN KEY (idKelas) REFERENCES kelas_belajar(id) ON DELETE CASCADE)");
        System.out.println("Migrate Kelas Belajar Quiz berhasil");
        connection.close();
    }

    public static void migratePertanyaanQuiz() throws SQLException {
        Connection connection = Config.getConnection();
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS pertanyaan_quiz (id INT PRIMARY KEY AUTO_INCREMENT, idQuiz INT, pertanyaan VARCHAR(255), FOREIGN KEY (idQuiz) REFERENCES kelas_belajar_quiz(id) ON DELETE CASCADE)");
        System.out.println("Migrate Pertanyaan Quiz berhasil");
        connection.close();
    }

    public static void migrateJawabanQuiz(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS jawaban_quiz (id INT PRIMARY KEY AUTO_INCREMENT, idPertanyaan INT, jawaban VARCHAR(255), isTrue BOOLEAN, FOREIGN KEY (idPertanyaan) REFERENCES pertanyaan_quiz(id) ON DELETE CASCADE)");

            System.out.println("Migrate Jawaban Quiz berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void migrateJawabanPengguna(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS jawaban_pengguna (id INT PRIMARY KEY AUTO_INCREMENT, idPengguna INT, idJawaban INT, FOREIGN KEY (idPengguna) REFERENCES pengguna(id) ON DELETE CASCADE, FOREIGN KEY (idJawaban) REFERENCES jawaban_quiz(id) ON DELETE CASCADE)");

            System.out.println("Migrate Jawaban Pengguna berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void migrateKelasOnline(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS kelas_online (id INT PRIMARY KEY AUTO_INCREMENT, idPelatihan INT, judulSeminar VARCHAR(255), gambarSeminar VARCHAR(255),waktuTanggalSeminar VARCHAR(255), pengajar VARCHAR(255), link VARCHAR(255), FOREIGN KEY (idPelatihan) REFERENCES pelatihan(id) ON DELETE CASCADE)");

            System.out.println("Migrate Kelas Online berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void migrateKelasOnlinePengguna(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS kelas_online_pengguna (id INT PRIMARY KEY AUTO_INCREMENT, idKelasOnline INT, idPengguna INT, FOREIGN KEY (idKelasOnline) REFERENCES kelas_online(id) ON DELETE CASCADE, FOREIGN KEY (idPengguna) REFERENCES pengguna(id) ON DELETE CASCADE)");

            System.out.println("Migrate Kelas Online Pengguna berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void migrateKelasPraktik(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS kelas_praktik (id INT PRIMARY KEY AUTO_INCREMENT, idPelatihan INT, judulPraktik VARCHAR(255), FOREIGN KEY (idPelatihan) REFERENCES pelatihan(id) ON DELETE CASCADE)");

            System.out.println("Migrate Kelas Praktik berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void migrateKelasPraktikPengguna(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS kelas_praktik_pengguna (id INT PRIMARY KEY AUTO_INCREMENT, idKelasPraktik INT, idPengguna INT, namaLengkap VARCHAR(255), tumbuhan VARCHAR(255), linkVideo VARCHAR(255), fase INT, FOREIGN KEY (idKelasPraktik) REFERENCES kelas_praktik(id) ON DELETE CASCADE, FOREIGN KEY (idPengguna) REFERENCES pengguna(id) ON DELETE CASCADE)");

            System.out.println("Migrate Kelas Praktik Pengguna berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void migrateLangganan(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS langganan (id INT PRIMARY KEY AUTO_INCREMENT, jenisLangganan VARCHAR(255), durasiLangganan VARCHAR(255), benefitLangganan VARCHAR(1000), hargaLangganan INT)");

            System.out.println("Migrate Langganan berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void migrateLanggananPengguna(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS langganan_pengguna (id INT PRIMARY KEY AUTO_INCREMENT, idLangganan INT, idPengguna INT, sampaiDengan VARCHAR(255), FOREIGN KEY (idLangganan) REFERENCES langganan(id) ON DELETE CASCADE, FOREIGN KEY (idPengguna) REFERENCES pengguna(id) ON DELETE CASCADE)");

            System.out.println("Migrate Langganan Pengguna berhasil");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dropAllTable() throws SQLException {
        Connection connection = Config.getConnection();
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS langganan_pengguna");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS langganan");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS lowongan_pengguna");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS lowongan");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS pelatihan_pengguna");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS kelas_belajar_video");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS jawaban_pengguna");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS jawaban_quiz");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS pertanyaan_quiz");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS kelas_belajar_quiz");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS kelas_online_pengguna");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS kelas_online");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS kelas_praktik_pengguna");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS kelas_praktik");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS kelas_belajar");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS pelatihan");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS pengguna");

        connection.close();
    }

}
