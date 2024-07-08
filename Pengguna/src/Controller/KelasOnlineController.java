package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import Main.App;

public class KelasOnlineController {

    @FXML
    private ComboBox<String> programCb;

    @FXML
    private Button ikutiButton;

    @FXML
    public void initialize() {
        programCb.getItems().addAll("Pertanian", "Perkebunan");

        ikutiButton.setOnMouseClicked(event -> handleIkuti());
    }
    
    @FXML
    private void handleBackButtonClick(MouseEvent event) throws Exception {
        App.showPelatihanView();
    }

    private void handleIkuti() {        
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informasi");
        alert.setHeaderText(null);
        alert.setContentText("Sesi belum dimulai!");
        alert.show();
    }
}
