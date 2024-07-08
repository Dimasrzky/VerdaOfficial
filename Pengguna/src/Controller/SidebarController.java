package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import util.Session;

import java.util.Optional;

import Main.App;

public class SidebarController {


    @FXML
    public void initialize() {
        // 
    }

    @FXML
    private void handleToBeranda(MouseEvent event) {
        try {
            App.showBerandaView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleToLowongan(MouseEvent event) {
        try {
            App.showLowonganView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleToPelatihan(MouseEvent event) {
        try {
            App.showPelatihanView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleToKomunitas(MouseEvent event) {
        try {
            App.showKomunitasView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleToLangganan(MouseEvent event) {
        try {
            App.showLanggananView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleLogout(MouseEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Logout");
        alert.setHeaderText(null);
        alert.setContentText("Apakah Anda yakin ingin logout?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Session.removeUser();
            try {
				App.showLoginView();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        }
    }
}
