package Controller;

import java.util.Optional;

import Main.App;
import Service.repository.LowonganRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TambahLowonganController {
	
	@FXML
	private Button simpanButton;
	
	@FXML
    private TextField posisiTf;

    @FXML
    private ComboBox<String> jenisWaktuCb;

    @FXML
    private TextField perusahaanTf;

    @FXML
    private TextField lokasiTf;

    @FXML
    private TextField gajiTf;

    @FXML
    private TextField kualifikasiTf;

    @FXML
    private DatePicker waktuDp;

    @FXML
    private TextField deskripsiTf;

    @FXML
    private TextField tanggungJawabTf;


    @FXML
    public void initialize() {
        jenisWaktuCb.getItems().addAll("Full Time", "Part Time", "Freelance", "Internship");

        simpanButton.setOnAction(event -> handleSimpanLowongan());
    }
    
    private void handleSimpanLowongan(){
            if (posisiTf.getText().isEmpty() || jenisWaktuCb.getValue() == null || perusahaanTf.getText().isEmpty() ||
                lokasiTf.getText().isEmpty() || gajiTf.getText().isEmpty() || kualifikasiTf.getText().isEmpty() ||
                waktuDp.getValue() == null || deskripsiTf.getText().isEmpty() || tanggungJawabTf.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Input tidak boleh kosong");
                alert.showAndWait();

            } else {
                try {
                    String posisi = posisiTf.getText();
                    String jenisWaktu = jenisWaktuCb.getValue();
                    String perusahaan = perusahaanTf.getText();
                    String lokasi = lokasiTf.getText();
                    int gaji = Integer.parseInt(gajiTf.getText());
                    String kualifikasi = kualifikasiTf.getText();
                    String waktuDiposting = waktuDp.getValue().toString();
                    String jobDesk = deskripsiTf.getText();
                    String tanggungJawab = tanggungJawabTf.getText();

                    LowonganRepository.addLowongan(posisi, perusahaan, lokasi, gaji, kualifikasi, waktuDiposting, jenisWaktu, jobDesk, tanggungJawab);

                    clearForm();
                    
                    showAlertAndNavigate("Sukses", "Lowongan berhasil ditambahkan");

                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Gaji harus berupa angka");
                    alert.showAndWait();
                }
            }
        }

    private void clearForm() {
        posisiTf.clear();
        jenisWaktuCb.setValue(null);
        perusahaanTf.clear();
        lokasiTf.clear();
        gajiTf.clear();
        kualifikasiTf.clear();
        waktuDp.setValue(null);
        deskripsiTf.clear();
        tanggungJawabTf.clear();
    }
    
    private void showAlertAndNavigate(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                App.showLowonganView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	    
	@FXML
    private void handleBackButtonClick(MouseEvent event) throws Exception {
        App.showLowonganView();;
    }
}