package Service.repository;

import Models.PelatihanPengguna;
import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PelatihanPenggunaRepository {
    public static PelatihanPengguna getSpecificPelatihanPengguna(int idPelatihan, int idPengguna){
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            var result = statement.executeQuery("SELECT * FROM pelatihan_pengguna WHERE idPelatihan = " + idPelatihan + " AND idPengguna = " + idPengguna);

            if (result.next()) {
                return new PelatihanPengguna(
                        result.getInt("id"),
                        result.getInt("idPelatihan"),
                        result.getInt("idPengguna"),
                        result.getString("status"),
                        result.getString("aktifHingga")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static ArrayList<PelatihanPengguna> getAllPelatihanPengguna (){
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            var result = statement.executeQuery("SELECT * FROM pelatihan_pengguna");
            ArrayList<PelatihanPengguna> pelatihanPengguna = new ArrayList<>();
            while (result.next()) {
                pelatihanPengguna.add(new PelatihanPengguna(
                        result.getInt("id"),
                        result.getInt("idPelatihan"),
                        result.getInt("idPengguna"),
                        result.getString("status"),
                        result.getString("aktifHingga")
                ));
            }
            connection.close();
            return pelatihanPengguna;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean acceptStatusDB(int idPelatihanPengguna){
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            statement.executeUpdate("UPDATE pelatihan_pengguna SET status = 'accepted' WHERE id = " + idPelatihanPengguna);
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean rejectStatusDB(int idPelatihanPengguna){
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            statement.executeUpdate("UPDATE pelatihan_pengguna SET status = 'rejected' WHERE id = " + idPelatihanPengguna);
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deletePelatihanPengguna(int idPelatihan, int idPengguna) {
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM pelatihan_pengguna WHERE idPelatihan = " + idPelatihan + " AND idPengguna = " + idPengguna);
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
