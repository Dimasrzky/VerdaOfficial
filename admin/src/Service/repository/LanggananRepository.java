package Service.repository;

import Models.Langganan;
import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class LanggananRepository {


    public static Langganan getLanggananPengguna(int idPengguna){
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            var result = statement.executeQuery("SELECT * FROM langganan JOIN langganan_pengguna ON langganan.id = langganan_pengguna.idLangganan WHERE langganan_pengguna.idPengguna = " + idPengguna);
            if (result.next()) {
                return new Langganan(
                        result.getInt("id"),
                        result.getString("jenis langganan"),
                        result.getString("durasi langganan"),
                        result.getString("benefit langganan"),
                        result.getInt("harga langganan")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Langganan getLanggananByIdDB(int id){
        try {
            Connection connection = Config.getConnection();
            var statement = connection.createStatement();
            var result = statement.executeQuery("SELECT * FROM langganan WHERE id = " + id);
            if (result.next()) {
                return new Langganan(
                        result.getInt("id"),
                        result.getString("jenisLangganan"),
                        result.getString("durasiLangganan"),
                        result.getString("benefitLangganan"),
                        result.getInt("hargaLangganan")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
