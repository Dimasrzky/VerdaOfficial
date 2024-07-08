package Controller;

import java.sql.SQLException;
import java.util.Optional;

import Main.App;
import Models.Lowongan;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import util.Session;

public class DaftarPelatihanController {
	@FXML
    private TextField namaTf;
	@FXML
    private ComboBox<String> genderCb;
	@FXML
    private TextField daerahTf;
	@FXML
    private ComboBox<String> programCb;
	@FXML
    private Button simpanButton;
	
	@FXML
    public void initialize() {
        genderCb.getItems().addAll("Laki-laki", "Perempuan");
        programCb.getItems().addAll("Pertanian", "Perkebunan");
        

        simpanButton.setOnAction(event -> {
			try {
				handleSimpanPelatihan();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

    }
	@FXML
    private void handleBackButtonClick(MouseEvent event) throws Exception {
        App.showPelatihanView();;
    }
	private void handleSimpanPelatihan() throws SQLException {
		String nama = namaTf.getText();
        String asalDaerah = daerahTf.getText();
        String gender = genderCb.getValue();
        String program = programCb.getValue();
        int idPelatihan = 0;
        
        if(program == null) {
        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Program harus dipilih");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    //
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return;
        }
        
        switch(program){
        case "Pertanian":
        	idPelatihan = 1;
        	break;
        case "Perkebunan":
        	idPelatihan = 2;
        	break;
        }
        
		boolean gabungPelatihan = Session.getUser().joinPelatihan(idPelatihan);
		if(gabungPelatihan) {
			showAlertAndNavigate("Sukses", "Berhasil bergabung ke pelatihan!");
		} else {
			showAlertAndNavigate("Gagal", "Gagal bergabung ke pelatihan!");
		}
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
