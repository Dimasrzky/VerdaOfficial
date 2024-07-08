package Controller;

import java.util.Optional;

import Main.App;
import Service.repository.PelatihanRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TambahPelatihanController {
	@FXML
	private Button simpanButton;
	
	@FXML
    private TextField idPenggunaTf;

    @FXML
    private ComboBox<String> programCb;

    @FXML
    private DatePicker aktifHinggaDp;


    @FXML
    public void initialize() {
        programCb.getItems().addAll("Pertanian", "Perkebunan");

        simpanButton.setOnAction(event -> handleSimpanPelatihan());
    }
    
    private void handleSimpanPelatihan(){
            if (idPenggunaTf.getText().isEmpty() || programCb.getValue() == null || aktifHinggaDp.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Input tidak boleh kosong");
                alert.showAndWait();

            } else {
                try {
                    String program = programCb.getValue();
                    int idPelatihan = 1;
                    int idPengguna = Integer.parseInt(idPenggunaTf.getText());
                    String aktifHingga = aktifHinggaDp.getValue().toString();
                    
                    switch(program){
                    case "Pertanian":
                    	idPelatihan = 1;
                    	break;
                    case "Perkebunan":
                    	idPelatihan = 2;
                    	break;
                    }


                    PelatihanRepository.joinPelatihanDB(idPelatihan, idPengguna);

                    clearForm();
                    
                    showAlertAndNavigate("Sukses", "Pengguna gabung pelatihan berhasil ditambahkan");

                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Id Pengguna berupa angka");
                    alert.showAndWait();
                }
            }
        }

    private void clearForm() {
    	idPenggunaTf.clear();
    	programCb.setValue(null);
        aktifHinggaDp.setValue(null);
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
	    
	@FXML
    private void handleBackButtonClick(MouseEvent event) throws Exception {
        App.showPelatihanView();;
    }
}
