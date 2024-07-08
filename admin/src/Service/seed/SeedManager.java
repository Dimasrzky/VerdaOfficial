package Service.seed;

import Service.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class SeedManager {
    public static void seed() {
    	seedAdmin();
        seedKomunitas();
    }

    public static void main(String[] args)  {
        seed();
    }

    public static void seedAdmin() {
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().execute("TRUNCATE admin");
            connection.createStatement().execute("INSERT INTO admin (email, password, username, profile_picture) VALUES ('admin@gmail.com', '123', 'aaa', 'aaa.jpg')");

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void seedKomunitas(){
        try {
            Connection connection = Config.getConnection();
            connection.createStatement().execute("TRUNCATE komunitas");
            connection.createStatement().execute("INSERT INTO komunitas (nama, kategori, visibility) VALUES ('Komunitas 1', 'Kategori 1', 'Public')");
            connection.createStatement().execute("INSERT INTO komunitas (nama, kategori, visibility) VALUES ('Komunitas 2', 'Kategori 2', 'Private')");
            connection.createStatement().execute("INSERT INTO komunitas (nama, kategori, visibility) VALUES ('Komunitas 3', 'Kategori 3', 'Public')");
            connection.createStatement().execute("INSERT INTO komunitas (nama, kategori, visibility) VALUES ('Komunitas 4', 'Kategori 1', 'Private')");
            connection.createStatement().execute("INSERT INTO komunitas (nama, kategori, visibility) VALUES ('Komunitas 5', 'Kategori 2', 'Public')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
