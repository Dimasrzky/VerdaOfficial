package Controller;

import java.util.Optional;

import Main.App;
import Service.repository.KomunitasRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TambahKomunitasController {
	@FXML
	private Button simpanButton;
	
	@FXML
    private TextField namaKomunitasTf;
	
	@FXML
    private TextField kategoriTf;

    @FXML
    private ComboBox<String> visibilityCb;


    @FXML
    public void initialize() {
    	visibilityCb.getItems().addAll("Public", "Private");

        simpanButton.setOnAction(event -> handleSimpanKomunitas());
    }
    
    private void handleSimpanKomunitas(){
            if (namaKomunitasTf.getText().isEmpty() || kategoriTf.getText().isEmpty() || visibilityCb.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Input tidak boleh kosong");
                alert.showAndWait();

            } else {
                String namaKomunitas = namaKomunitasTf.getText();
                String kategori = kategoriTf.getText();
                String visibility = visibilityCb.getValue();

                KomunitasRepository.addKomunitas(namaKomunitas, kategori, visibility);

                clearForm();
                
                showAlertAndNavigate("Sukses", "Komunitas berhasil ditambahkan");
            }
        }

    private void clearForm() {
    	namaKomunitasTf.clear();
    	kategoriTf.clear();
    	visibilityCb.setValue(null);
    }
    
    private void showAlertAndNavigate(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                App.showKomunitasView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	    
	@FXML
    private void handleBackButtonClick(MouseEvent event) throws Exception {
        App.showKomunitasView();;
    }
}
