package util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import Main.App;

import java.util.Optional;

public class AuthInterceptor {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void checkSession() {
        if (Session.getUser() == null) {
            showAlertAndNavigate();
        }
    }

    private static void showAlertAndNavigate() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Kamu harus login dahulu!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Session.removeUser();
            try {
            	App.showLoginView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
