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
}
