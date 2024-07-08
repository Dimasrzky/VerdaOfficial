package Controller;

import Main.App;
import Models.Pengguna;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import util.Session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BerandaController {

    @FXML
    private TextField searchField;

    @FXML
    private ImageView profileImage;

    @FXML
    private Text profileName;

    @FXML
    private Text dateText;

    @FXML
    private Text timeText;

    @FXML
    private Text quoteText;

    @FXML
    private Text authorText;

    @FXML
    private ImageView masukLowongan;

    @FXML
    public void initialize() {
        Pengguna dataPengguna = Session.getUser();
        // Inisialisasi data di sini
        profileName.setText(dataPengguna.getNamaPengguna());
        
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, MMM dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
        dateText.setText(currentDate.format(dateFormatter));
        timeText.setText(currentTime.format(timeFormatter));
        
        quoteText.setText("\"Langkah kecil untuk bumi, langkah besar untuk masa depan.\"");
        authorText.setText("- Max Verstappen");
    }

    @FXML
    private void handleSearch() {
        String searchQuery = searchField.getText();
        // Logika pencarian di sini
        System.out.println("Mencari: " + searchQuery);
    }
}
