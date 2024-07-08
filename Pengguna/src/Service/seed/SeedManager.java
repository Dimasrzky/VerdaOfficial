package Service.seed;

import java.sql.SQLException;

public class SeedManager {
    public static void seed() throws SQLException {
        SeedPengguna.SeedPengguna();
        SeedLowongan.SeedLowongan();
        SeedLowonganPengguna.SeedLowonganPengguna();
        SeedPelatihan.SeedPelatihan();
        SeedPelatihanPengguna.SeedPelatihanPengguna();
        SeedKelasBelajar.SeedKelasBelajar();
        SeedKelasBelajarQuiz.SeedKelasBelajarQuiz();
        SeedKelasBelajarVideo.SeedKelasBelajarVideo();
        SeedPertanyaanQuiz.SeedPertanyaanQuiz();
        SeedJawabanQuiz.SeedJawabanQuiz();
        SeedJawabanPengguna.SeedJawabanPengguna();
        SeedKelasOnline.SeedKelasOnline();
        SeedKelasOnlinePengguna.SeedKelasOnlinePengguna();
        SeedKelasPraktik.SeedKelasPraktik();
        SeedKelasPraktikPengguna.SeedKelasPraktikPengguna();
        SeedLangganan.SeedLangganan();
        SeedLanggananPengguna.seedLanggananPengguna();
    }

    public static void main(String[] args) throws SQLException {
        seed();
    }
}
