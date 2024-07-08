package Service.repository;

import Models.Pelatihan;
import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PelatihanRepository {
    public static ArrayList<Pelatihan> getAllPelatihanDB() {
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            var result = statement.executeQuery("SELECT * FROM pelatihan");
            ArrayList<Pelatihan> pelatihanArrayList = new ArrayList<>();
            while (result.next()) {
                Pelatihan pelatihan = new Pelatihan(
                        result.getInt("id"),
                        result.getString("namaPelatihan")
                );
                pelatihanArrayList.add(pelatihan);
            }
            return pelatihanArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Pelatihan> getAllPelatihanByUserIDDB(int userId) {
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            var result = statement.executeQuery("SELECT * FROM pelatihan WHERE id IN (SELECT idPelatihan FROM pelatihan_pengguna WHERE idPengguna = " + userId + ")");
            ArrayList<Pelatihan> pelatihanArrayList = new ArrayList<>();
            while (result.next()) {
                Pelatihan pelatihan = new Pelatihan(
                        result.getInt("id"),
                        result.getString("namaPelatihan")
                );
                pelatihanArrayList.add(pelatihan);
            }
            return pelatihanArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean joinPelatihanDB(int idPelatihan, int idPengguna) {
    	LocalDate currentDate = LocalDate.now();        
        LocalDate futureDate = currentDate.plusMonths(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = futureDate.format(formatter);
        
        Connection connection = null;
        try {
            connection = Config.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            connection.createStatement().executeUpdate("INSERT INTO pelatihan_pengguna (idPelatihan, idPengguna, status, aktifHingga) VALUES ('" + idPelatihan + "', '" + idPengguna + "', 'pending', '" + formattedDate + "')");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
