package util;

import Models.Pengguna;
import Service.repository.PenggunaRepository;

import java.sql.SQLException;

public class Session {
    private static Pengguna user;

    public static Pengguna getUser() {
        return user;
    }

    public static void setUser(Pengguna user) {
        Session.user = user;
    }

    public static void removeUser() {
        Session.user = null;
    }

    public static boolean login(String username, String password) throws SQLException {
        Pengguna newUser = PenggunaRepository.loginDB(username, password);
        if (newUser != null) {
            Session.setUser(newUser);
            return true;
        }
        return false;
    }

    public static boolean register(String username, String email, String password) {
        if (username.equals("") || email.equals("") || password.equals("")) {
            return false;
        }
        if (username.length() < 5 || email.length() < 5 || password.length() < 5) {
            return false;
        }
        if (!username.matches(".*[a-z].*") || !username.matches(".*[A-Z].*") || !username.matches(".*[0-9].*")) {
            return false;
        }
        if (PenggunaRepository.registerDB(username, email, password)) {
            return true;
        }
        return false;
    }
}
