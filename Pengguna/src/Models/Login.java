package Models;

public class Login {

    public boolean login(String email, String password) {
        return "user123".equals(email) && "123".equals(password);
    }
}
