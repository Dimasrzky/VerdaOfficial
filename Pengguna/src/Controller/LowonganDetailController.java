package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Main.App;
import Models.Lowongan;

public class LowonganDetailController {
    @FXML
    private Text perusahaanText;
    @FXML
    private Text posisiText;
    @FXML
    private Text lokasiText;
    @FXML
    private Text jenisWaktuText;
    @FXML
    private Text kualifikasiText;
    @FXML
    private Text gajiText;
    @FXML
    private Text tanggungJawabText;
    @FXML
    private Text tentangPeranText;
    @FXML
    private Button melamarButton;

    public void initData(Lowongan lowongan) {
        perusahaanText.setText(lowongan.getPerusahaan());
        posisiText.setText(lowongan.getPosisi());
        lokasiText.setText(lowongan.getLokasi());
        jenisWaktuText.setText(lowongan.getJenisWaktu());
        kualifikasiText.setText(lowongan.getKualifikasi());
        gajiText.setText("Rp " + String.format("%,d", lowongan.getGaji()));
        tanggungJawabText.setText(lowongan.getJobDesk());
        tentangPeranText.setText(lowongan.getTanggungJawab());
        melamarButton.setOnAction(event -> handleMelamar(lowongan));
    }
    @FXML
    private void handleBackButtonClick(MouseEvent event) throws Exception {
        App.showLowonganView();
    }
    private void handleMelamar(Lowongan lowongan) {
        try {
            App.showLamaranPekerjaanView(lowongan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}