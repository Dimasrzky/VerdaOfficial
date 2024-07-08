package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import util.Session;
import javafx.event.ActionEvent;

import java.util.Optional;

import Main.App;

public class RegistrasiController {

    @FXML
    private TextField TfUsername;

    @FXML
    private TextField TfEmail;

    @FXML
    private PasswordField PfPassword;

    @FXML
    private PasswordField PfConfirm;

    @FXML
    private Text SuccesRegister;

    @FXML
    private Text errorRegister;

    @FXML
    private Hyperlink LinkLogin;

    @FXML
    public void initialize() {
        errorRegister.setVisible(false);
        SuccesRegister.setVisible(false);
    }

    @FXML
    private void handleRegisterClickIcon() {
        String username = TfUsername.getText();
        String email = TfEmail.getText();
        String password = PfPassword.getText();
        String confirmPassword = PfConfirm.getText();

        if (!isValidUsername(username)) {
            showError("Username harus memiliki huruf besar, kecil, dan angka");
            return;
        }

        if (!isValidEmail(email)) {
            showError("Email tidak valid");
            return;
        }
        if (password.length() == 0) {
        	showError("Password tidak boleh kosong");
            return;
        }
        if (!password.equals(confirmPassword)) {
            showError("Password dan konfirmasi password tidak cocok");
            return;
        }

        boolean isRegistered = Session.register(username, email, password);

        if (isRegistered) {
            showSuccess("Registrasi berhasil!");
            showAlertAndNavigate("Sukses", "Registrasi berhasil!");
        } else {
            showError("Registrasi gagal, coba lagi.");
        }
    }

    @FXML
    private void handleBackToLogin(ActionEvent event) {
        try {
            App.showLoginView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        errorRegister.setText(message);
        errorRegister.setVisible(true);
        SuccesRegister.setVisible(false);
    }

    private void showSuccess(String message) {
        SuccesRegister.setText(message);
        SuccesRegister.setVisible(true);
        errorRegister.setVisible(false);
    }

    private void showAlertAndNavigate(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                App.showLoginView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isValidUsername(String username) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
        return username.matches(regex);
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        return email.matches(regex);
    }
}
