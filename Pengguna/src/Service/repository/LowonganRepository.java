package Service.repository;

import Models.Lowongan;
import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LowonganRepository {
    public static boolean joinLowonganDB(int idLowongan, int idPengguna, String status, String namaLengkap, String gender, String asalDaerah, String pendidikanTerakhir, String tentangSaya) throws SQLException {
        Connection connection = null;
        try {
            connection = Config.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            connection.createStatement().executeUpdate("INSERT INTO lowongan_pengguna (idLowongan, idPengguna, status, namaLengkap, gender, asalDaerah, pendidikanTerakhir, tentangSaya) VALUES ('" + idLowongan + "', '" + idPengguna + "', '" + status + "', '" + namaLengkap + "', '" + gender + "', '" + asalDaerah + "', '" + pendidikanTerakhir + "', '" + tentangSaya + "')");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Lowongan> getAllLowonganDB(){
        Connection connection = null;
        try {
            connection = Config.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            var result = connection.createStatement().executeQuery("SELECT * FROM lowongan");
            ArrayList<Lowongan> lowongans = new ArrayList<>();
            while (result.next()) {
                Lowongan newLowongan = new Lowongan(result.getString("posisi"), result.getString("perusahaan"), result.getString("lokasi"), result.getInt("gaji"), result.getString("kualifikasi"), result.getString("waktuDiposting"), result.getString("jenisWaktu"), result.getString("jobDesk"), result.getString("tanggungJawab"));
                newLowongan.setId(result.getInt("id"));
                lowongans.add(newLowongan);
            }
            return lowongans;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
