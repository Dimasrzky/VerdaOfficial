package Service.migrate;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class MigrateManager {
    public static void migrate(){
    	migrateAdmin();
        migrateKomunitas();
    }

    public static void main(String[] args) {
        migrate();
    }

    public static void migrateAdmin(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS admin (id INT PRIMARY KEY AUTO_INCREMENT, email VARCHAR(255), password VARCHAR(255), username VARCHAR(255), profile_picture VARCHAR(255))");
            System.out.println("Table Admin berhasil dibuat");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void migrateKomunitas(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS komunitas (id INT PRIMARY KEY AUTO_INCREMENT, nama VARCHAR(255), kategori VARCHAR(255), visibility VARCHAR(255))");
            System.out.println("Table Komunitas berhasil dibuat");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
