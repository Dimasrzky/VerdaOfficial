package util;

import Models.Admin;
import Models.Pengguna;
import Service.repository.AdminRepository;
import Service.repository.PenggunaRepository;

import java.sql.SQLException;

public class Session {
    private static Admin user;

    public static Admin getUser() {
        return user;
    }

    public static void setUser(Admin user) {
        Session.user = user;
    }

    public static void removeUser() {
        Session.user = null;
    }

    public static boolean login(String email, String password) throws SQLException {
        Admin newUser = AdminRepository.loginAdmin(email, password);
        if (newUser != null) {
            Session.setUser(newUser);
            return true;
        }
        return false;
    }

    public static boolean register(String username, String email, String password, String profile_picture) {
        if (username.equals("") || email.equals("") || password.equals("")) {
            return false;
        }
        if (username.length() < 5 || email.length() < 5 || password.length() < 5) {
            return false;
        }
        if (!username.matches(".*[a-z].*") || !username.matches(".*[A-Z].*") || !username.matches(".*[0-9].*")) {
            return false;
        }
        if (AdminRepository.registerDB(username, email, password, profile_picture)) {
            return true;
        }
        return false;
    }
}
