package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.Session;

import java.io.File;
import java.util.Optional;

import Main.App;
import Models.Pengguna;

public class KelasPraktikController {

    @FXML
    private TextField namaTf;

    @FXML
    private ComboBox<Integer> faseCb;

    @FXML
    private ComboBox<String> tumbuhanCb;

    @FXML
    private ComboBox<String> uploadCb;

    @FXML
    private Button kirimButton;

    @FXML
    public void initialize() {
        faseCb.getItems().addAll(1, 2, 3, 4, 5);
        tumbuhanCb.getItems().addAll("Wortel", "Tomat", "Cabai");

        uploadCb.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
            	uploadCb.setValue(file.getName());
            }
        });

        kirimButton.setOnMouseClicked(event -> handleSubmit());
    }
    
    @FXML
    private void handleBackButtonClick(MouseEvent event) throws Exception {
        App.showPelatihanView();
    }

    private void handleSubmit() {
    	Pengguna currentUser = Session.getUser();
        String nama = namaTf.getText();
        int id = currentUser.getId();
        Integer fase = faseCb.getValue();
        String tumbuhan = tumbuhanCb.getValue();
        String videoFile = uploadCb.getValue();

        System.out.println("Nama: " + nama);
        System.out.println("ID: " + id);
        System.out.println("Fase: " + fase);
        System.out.println("Tumbuhan: " + tumbuhan);
        System.out.println("Video: " + videoFile);
        
        showAlertAndNavigate("Sukses", "Progress Praktik berhasil dikirim");
    }
    
    private void showAlertAndNavigate(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                App.showPelatihanView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
