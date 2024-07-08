package Service.repository;

import Models.LowonganPengguna;
import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LowonganPenggunaRepository {
    public static ArrayList<LowonganPengguna> getAllLowonganPengguna(){
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            var result = statement.executeQuery("SELECT * FROM lowongan_pengguna");
            ArrayList<LowonganPengguna> lowonganPengguna = new ArrayList<>();
            while (result.next()) {
            	lowonganPengguna.add(new LowonganPengguna(
            			result.getInt("id"), 
                        result.getInt("idLowongan"),
                        result.getInt("idPengguna"), 
                        result.getString("status"),
                        result.getString("namaLengkap"), 
                        result.getString("gender"), 
                        result.getString("asalDaerah"), 
                        result.getString("pendidikanTerakhir"), 
                        result.getString("tentangSaya")
                ));
            }
            connection.close();
            return lowonganPengguna;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addLowonganPengguna(int idLowongan, int idPengguna, String status, String namaLengkap, String gender, String asalDaerah, String pendidikanTerakhir, String tentangSaya) throws SQLException {
        return LowonganRepository.joinLowonganDB(idLowongan, idPengguna, status, namaLengkap, gender, asalDaerah, pendidikanTerakhir, tentangSaya);
    }
    
    public static boolean deleteLowonganPengguna(int idLowongan, int idPengguna) {
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM lowongan_pengguna WHERE idLowongan = " + idLowongan + " AND idPengguna = " + idPengguna);
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
