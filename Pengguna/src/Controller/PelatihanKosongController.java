package Controller;

import java.util.ArrayList;

import Main.App;
import Models.Pelatihan;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import util.Session;

public class PelatihanKosongController {
    @FXML
    private ImageView profileImage;

    @FXML
    private ImageView masukLowongan;

    @FXML
    private TextField searchField;
    
    @FXML
    private Button gabungButton;

    @FXML
    public void initialize() {
        gabungButton.setOnAction(event -> handleGabung());
    }
    
    private void handleGabung() {
    	try {
			App.showDaftarPelatihanView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
