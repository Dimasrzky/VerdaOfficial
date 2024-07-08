package Service.repository;

import Models.Lowongan;
import Models.LowonganPengguna;
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

    public static ArrayList<LowonganPengguna> getAllLowonganPenggunaDB() {
        Connection connection = null;
        try {
            connection = Config.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            var result = connection.createStatement().executeQuery("SELECT * FROM lowongan_pengguna");
            ArrayList<LowonganPengguna> lowonganPenggunas = new ArrayList<>();
            while (result.next()) {
                LowonganPengguna newLowonganPengguna = new LowonganPengguna(result.getInt("id"), result.getInt("idLowongan"), result.getInt("idPengguna"), result.getString("status"), result.getString("namaLengkap"), result.getString("gender"), result.getString("asalDaerah"), result.getString("pendidikanTerakhir"), result.getString("tentangSaya"));
                lowonganPenggunas.add(newLowonganPengguna);
            }
            return lowonganPenggunas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Lowongan getSpecificLowonganDB(int id) {
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            var result = statement.executeQuery("SELECT * FROM lowongan WHERE id = " + id);
            if (result.next()) {
                return new Lowongan(
                        result.getString("posisi"),
                        result.getString("perusahaan"),
                        result.getString("lokasi"),
                        result.getInt("gaji"),
                        result.getString("kualifikasi"),
                        result.getString("waktuDiposting"),
                        result.getString("jenisWaktu"),
                        result.getString("jobDesk"),
                        result.getString("tanggungJawab")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean acceptStatusDB(int idLowonganPengguna){
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            statement.executeUpdate("UPDATE lowongan_pengguna SET status = 'accepted' WHERE id = " + idLowonganPengguna);
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean rejectStatusDB(int idLowonganPengguna){
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            statement.executeUpdate("UPDATE lowongan_pengguna SET status = 'rejected' WHERE id = " + idLowonganPengguna);
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addLowongan(String posisi, String perusahaan, String lokasi, int gaji, String kualifikasi, String waktuDiposting, String jenisWaktu, String jobDesk, String tanggungJawab) {
        Connection connection = null;
        try {
            connection = Config.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.createStatement().executeUpdate("INSERT INTO lowongan (posisi, perusahaan, lokasi, gaji, kualifikasi, waktuDiposting, jenisWaktu, jobDesk, tanggungJawab) VALUES ('" + posisi + "', '" + perusahaan + "', '" + lokasi + "', '" + gaji + "', '" + kualifikasi + "', '" + waktuDiposting + "', '" + jenisWaktu + "', '" + jobDesk + "', '" + tanggungJawab + "')");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteLowongan(int id) {
        Connection connection = null;
        try {
            connection = Config.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.createStatement().executeUpdate("DELETE FROM lowongan WHERE id = " + id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
