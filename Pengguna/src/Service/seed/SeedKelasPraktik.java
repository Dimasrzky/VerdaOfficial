package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedKelasPraktik {
    public static void SeedKelasPraktik() {
       try{
              Connection connection = Config.getConnection();
              truncateKelasPraktik();
                connection.createStatement().executeUpdate("INSERT INTO kelas_praktik (idPelatihan, judulPraktik) VALUES (1, 'Praktik Menanam Wortel')");
                connection.createStatement().executeUpdate("INSERT INTO kelas_praktik (idPelatihan, judulPraktik) VALUES (1, 'Praktik Menanam Tomat')");

              System.out.println("Seed Kelas Praktik berhasil");
              connection.close();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    private static void truncateKelasPraktik() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().executeUpdate(" SET FOREIGN_KEY_CHECKS = 0");
            connection.createStatement().executeUpdate("TRUNCATE TABLE kelas_praktik");
            connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
