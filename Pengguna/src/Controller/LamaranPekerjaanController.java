package Controller;

import java.io.File;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.Session;

public class LamaranPekerjaanController {
	private Lowongan dataLowongan;
	
	@FXML
    private TextField namaTf;
	@FXML
    private ComboBox<String> genderCb;
	@FXML
    private TextField daerahTf;
	@FXML
    private ComboBox<String> pendidikanCb;
	@FXML
    private ComboBox<String> sertifikatCb;
	@FXML
    private TextArea tentangTf;
	@FXML
    private Button simpanButton;
	
	public void initData(Lowongan lowongan) {
        this.setDataLowongan(lowongan);
        
    }
	public void setDataLowongan(Lowongan lowongan) {
		this.dataLowongan = lowongan;
	}
	
	@FXML
    public void initialize() {
        genderCb.getItems().addAll("Laki-laki", "Perempuan");
        pendidikanCb.getItems().addAll("SMA", "D3", "D4/S1");
        
        sertifikatCb.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
            	sertifikatCb.setValue(file.getName());
            }
        });

        simpanButton.setOnAction(event -> handleSimpanLowongan());

    }
	@FXML
    private void handleBackButtonClick(MouseEvent event) throws Exception {
        App.showLowonganDetailView(dataLowongan);
    }
	private void handleSimpanLowongan() {
		int idLowongan = dataLowongan.getId();
		String status = "waiting";
		String namaLengkap = namaTf.getText();
        String asalDaerah = daerahTf.getText();
        String tentangSaya = tentangTf.getText();
        String gender = genderCb.getValue();
        String pendidikanTerakhir = pendidikanCb.getValue();
		try {
			
			boolean ajukanLowongan = Session.getUser().joinLowongan(idLowongan, status, namaLengkap, gender, asalDaerah, pendidikanTerakhir, tentangSaya);
			if(ajukanLowongan) {
				showAlertAndNavigate("Sukses", "Lowongan berhasil diajukan!");
			} else {
				showAlertAndNavigate("Gagal", "Lowongan gagal diajukan!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
                App.showBerandaView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
