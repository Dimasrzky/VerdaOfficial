package Controller;

import java.util.Optional;

import Main.App;
import Models.Pengguna;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.Session;

public class LanggananController {
	@FXML
	private VBox langgananEconomy;
	@FXML
	private VBox langgananPremium;
	@FXML
	private HBox overlay;
	@FXML
	private HBox pembayaranSheet;
	@FXML
	private Text paketLanggananText;
	@FXML
	private Text hargaText;
	@FXML
	private ComboBox metodeBayarCb;
	@FXML
	private Button bayarButton;
	
	private Pengguna currentUser;
	
	@FXML
    public void initialize() {
		metodeBayarCb.getItems().addAll("Gopay", "OVO", "Dana", "ShopeePay", "Kartu Debit", "Kartu Kredit");
		
        overlay.setVisible(false);
        pembayaranSheet.setVisible(false);
        
        currentUser = Session.getUser();
        
        overlay.setOnMouseClicked(event -> handleOverlay());
        
        langgananEconomy.setOnMouseClicked(event -> handleOpenBayar(1));
        langgananPremium.setOnMouseClicked(event -> handleOpenBayar(2));
    }
	
	private void handleOverlay() {
        pembayaranSheet.setVisible(false);
        overlay.setVisible(false);
	}
	private void handleOpenBayar(int id) {
		overlay.setVisible(true);
        pembayaranSheet.setVisible(true);
        if(id == 1) {
        	paketLanggananText.setText("KelasEconomy");
        	hargaText.setText("Rp " + String.format("%,d", 150000));
        	bayarButton.setOnMouseClicked(action -> handleBayar(1));
        } else if(id == 2) {
        	paketLanggananText.setText("KelasPremium");
        	hargaText.setText("Rp " + String.format("%,d", 756000));
        	bayarButton.setOnMouseClicked(action -> handleBayar(2));
        }
	}
	
	private void handleBayar(int id) {
		if(currentUser.joinLangganan(id)) {
			showAlertAndNavigate("Sukses", "Anda berhasil berlangganan " + paketLanggananText.getText());
		} else {
			showAlertAndNavigate("Gagal", "Berlangganan gagal");
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
