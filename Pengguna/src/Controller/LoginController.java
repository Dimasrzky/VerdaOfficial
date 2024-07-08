package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import util.Session;

import java.sql.SQLException;
import java.util.Optional;

import Main.App;

public class LoginController {

    @FXML
    private TextField TfEmail;

    @FXML
    private PasswordField PfPassword;

    @FXML
    private Text errorMessage;

    @FXML
    private Hyperlink DaftarSekarang;

    @FXML
    private Hyperlink LupaPassword;

    @FXML
    private void handleIconClickLogin(MouseEvent event) throws SQLException {
        String email = TfEmail.getText();
        String password = PfPassword.getText();

        if (Session.login(email, password)) {
            errorMessage.setVisible(false);
            showInfoAlert("Sukses", "Login berhasil!");
            navigateToBeranda();
        } else {
            errorMessage.setVisible(true);
            System.out.println("Login gagal");
        }
    }

    @FXML
    private void handleDaftarSekarangAction(ActionEvent event) {
        try {
            App.showRegisterView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showInfoAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void navigateToBeranda() {
        try {
            App.showBerandaView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
