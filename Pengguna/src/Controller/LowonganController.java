package Controller;

import Models.Lowongan;
import Service.repository.LowonganRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import Main.App;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LowonganController {
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vboxLowongan;

    private LowonganRepository lowonganRepository = new LowonganRepository();
    private ObservableList<Lowongan> displayedLowongan = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        comboBox.getItems().addAll("Paling populer", "Postingan terbaru", "Gaji tertinggi");

        loadAndDisplayLowongan();

        comboBox.setOnAction(event -> {
            String selectedSort = comboBox.getValue();
            if (selectedSort != null) {
                switch (selectedSort) {
                    case "Postingan terbaru":
                        sortAndDisplayByLatest();
                        break;
                    case "Gaji tertinggi":
                        sortAndDisplayBySalary();
                        break;
                    case "Paling populer":
                        sortAndDisplayByPopularity();
                        break;
                    default:
                        break;
                }
            }
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchLowongan(newValue);
        });
    }

    private void loadAndDisplayLowongan() {
        List<Lowongan> lowonganList = lowonganRepository.getAllLowonganDB();
        displayedLowongan.clear();
        displayedLowongan.addAll(lowonganList);
        displayLowongan(displayedLowongan);
    }

    private void displayLowongan(List<Lowongan> lowonganList) {
        vboxLowongan.getChildren().clear();
        HBox row = null;
        int lowonganCount = 0;
        vboxLowongan.setAlignment(Pos.TOP_LEFT);

        for (Lowongan lowongan : lowonganList) {
            if (lowonganCount % 2 == 0) {
                row = new HBox(20); 
                vboxLowongan.getChildren().add(row);
            }
            lowonganCount++;

            VBox vbox = createLowonganUI(lowongan);
            row.getChildren().add(vbox);
        }
    }

    private VBox createLowonganUI(Lowongan lowongan) {
        VBox vbox = new VBox();
        vbox.getStyleClass().add("tabel-Lowongan");
        vbox.setMinHeight(256.0);
        vbox.setMinWidth(314.0);
        
        vbox.setAlignment(Pos.TOP_LEFT);

        ImageView logoPerusahaan = new ImageView(new Image(getClass().getResourceAsStream("../Styles/Image/logo perusahaan.png")));
        logoPerusahaan.setFitHeight(74.0);
        logoPerusahaan.setFitWidth(74.0);

        Text perusahaanTextBold = new Text(lowongan.getPerusahaan());
        perusahaanTextBold.setFont(new javafx.scene.text.Font("Arial Bold", 12.0));
        perusahaanTextBold.setTextAlignment(TextAlignment.CENTER);

        VBox logo = new VBox(logoPerusahaan, perusahaanTextBold);
        logo.setSpacing(4);
        logo.setAlignment(Pos.TOP_CENTER);

        ImageView verifikasiIcon = new ImageView(new Image(getClass().getResourceAsStream("../Styles/Image/icon verifikasi.png")));
        verifikasiIcon.setFitHeight(19.0);
        verifikasiIcon.setFitWidth(23.0);

        Text perusahaanText = new Text(lowongan.getPerusahaan());
        perusahaanText.setFill(javafx.scene.paint.Color.web("#0f9ceb"));
        perusahaanText.setFont(new javafx.scene.text.Font(10));

        Text posisiText = new Text(lowongan.getPosisi());
        posisiText.setFont(new javafx.scene.text.Font("Arial Bold", 18.0));

        Text lokasiText = new Text(lowongan.getLokasi());
        lokasiText.setFont(new javafx.scene.text.Font(10));

        HBox perusahaanName = new HBox(verifikasiIcon, perusahaanText);
        perusahaanName.setAlignment(Pos.CENTER_LEFT);

        VBox jobDetail = new VBox(posisiText, perusahaanName, lokasiText);
        jobDetail.setAlignment(Pos.CENTER_LEFT);

        HBox jobHeader = new HBox(logo, jobDetail);
        jobHeader.setSpacing(14);

        ImageView waktuIcon = new ImageView(new Image(getClass().getResourceAsStream("../Styles/Image/icon jam.png")));
        waktuIcon.setFitHeight(22.0);
        waktuIcon.setFitWidth(19.0);

        Text statusText = new Text(lowongan.getJenisWaktu());
        statusText.setFont(new javafx.scene.text.Font(10.0));

        HBox jenisWaktu = new HBox(waktuIcon, statusText);
        jenisWaktu.setAlignment(Pos.CENTER_LEFT);
        jenisWaktu.setSpacing(10);

        ImageView pendidikanIcon = new ImageView(new Image(getClass().getResourceAsStream("../Styles/Image/icon pendidikan.png")));
        pendidikanIcon.setFitHeight(21.0);
        pendidikanIcon.setFitWidth(22.0);

        Text pendidikanText = new Text(lowongan.getKualifikasi());
        pendidikanText.setFont(new javafx.scene.text.Font(10.0));

        HBox minPendidikan = new HBox(pendidikanIcon, pendidikanText);
        minPendidikan.setSpacing(10);
        minPendidikan.setAlignment(Pos.CENTER_LEFT);

        ImageView gajiIcon = new ImageView(new Image(getClass().getResourceAsStream("../Styles/Image/icon gaji.png")));
        gajiIcon.setFitHeight(20.0);
        gajiIcon.setFitWidth(20.0);

        Text gajiText = new Text("Rp " + String.format("%,d", lowongan.getGaji()));
        gajiText.setFont(new javafx.scene.text.Font(10.0));

        HBox gaji = new HBox(gajiIcon, gajiText);
        gaji.setAlignment(Pos.CENTER_LEFT);
        gaji.setSpacing(10);

        VBox lowonganDetail = new VBox(minPendidikan, gaji, jenisWaktu);
        VBox.setMargin(lowonganDetail, new Insets(5, 0, 5, 0));

        Line garisBawah = new Line();
        garisBawah.setEndX(201.8928680419922);
        garisBawah.setEndY(-6.213382244110107);
        garisBawah.setStartX(-100.0);
        garisBawah.setStartY(-6.21339225769043);
        garisBawah.setStroke(javafx.scene.paint.Color.web("#b1b1b1"));
        VBox.setMargin(garisBawah, new Insets(10, 0, 10, 0));

        Button lihatDetailButton = new Button("Lihat detail");
        lihatDetailButton.setPrefHeight(26.0);
        lihatDetailButton.setPrefWidth(300.0);
        lihatDetailButton.setOnAction(event -> handleLihatDetail(lowongan));

        Text waktuText = new Text(lowongan.getWaktuDiposting());
        waktuText.setFill(javafx.scene.paint.Color.web("#717171"));
        waktuText.setFont(new javafx.scene.text.Font("Arial", 10.0));

        vbox.getChildren().addAll(jobHeader, lowonganDetail, lihatDetailButton, garisBawah, waktuText);

        return vbox;
    }

    private void sortAndDisplayByLatest() {
        List<Lowongan> sortedList = displayedLowongan.stream()
                .sorted(Comparator.comparing(Lowongan::getWaktuDiposting).reversed())
                .collect(Collectors.toList());
        displayLowongan(sortedList);
    }

    private void sortAndDisplayBySalary() {
        List<Lowongan> sortedList = displayedLowongan.stream()
                .sorted(Comparator.comparingInt(Lowongan::getGaji).reversed())
                .collect(Collectors.toList());
        displayLowongan(sortedList);
    }

    private void sortAndDisplayByPopularity() {
    	//
    }

    private void searchLowongan(String keyword) {
        List<Lowongan> filteredList = displayedLowongan.stream()
                .filter(lowongan -> lowongan.getPosisi().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        displayLowongan(filteredList);
    }
    
    private void handleLihatDetail(Lowongan lowongan) {
        try {
            App.showLowonganDetailView(lowongan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}