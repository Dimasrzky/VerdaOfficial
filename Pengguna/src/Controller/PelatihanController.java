package Controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Main.App;
import Models.KelasBelajar;
import Models.Langganan;
import Models.Pelatihan;
import Models.Pengguna;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import util.Session;

public class PelatihanController {
    @FXML
    private Text langgananNamaText;
    @FXML
    private Text langgananIdText;
    @FXML
    private Text langgananWaktuText;
    @FXML
    private ComboBox<String> programCb;
    @FXML
    private VBox vboxMateri;
    @FXML
    private VBox langgananBox;
    @FXML
    private VBox kelasOnlineBox;
    @FXML
    private VBox kelasPraktikBox;

    private ArrayList<Pelatihan> pelatihanUser;
    private ArrayList<KelasBelajar> kelasBelajar;
    private ObservableList<KelasBelajar> displayedKelasBelajar = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
    	Pengguna currentUser = Session.getUser();
    	
    	LocalDate currentDatePlusThreeMonths = LocalDate.now().plusMonths(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd, MMMM yyyy");
        
        kelasPraktikBox.setOnMouseClicked(action -> handleKelasPraktik());
        kelasOnlineBox.setOnMouseClicked(action -> handleKelasOnline());
        
    	langgananNamaText.setText(currentUser.getNamaPengguna());
    	langgananIdText.setText(Integer.toString(currentUser.getId()));
    	langgananWaktuText.setText(currentDatePlusThreeMonths.format(formatter));
    	
    	Langganan userLangganan = currentUser.getLangganan();
    	
    	if(userLangganan == null) {
    		langgananBox.setVisible(false);
    		langgananBox.setManaged(false);
    	} else {
    		langgananBox.setVisible(true);
    		langgananBox.setManaged(true);
    		if (userLangganan.getJenisLangganan().equals("Economy")) {
    	        langgananBox.getStyleClass().add("economy-gradient");
    	        langgananWaktuText.setStyle("-fx-fill: #000000;");
    	    } else {
    	        langgananBox.getStyleClass().add("premium-gradient");
    	        langgananWaktuText.setStyle("-fx-fill: #fcc21b;");
    	    }
    	}
    	
        pelatihanUser = currentUser.getAllUserPelatihan();

        if (pelatihanUser != null && !pelatihanUser.isEmpty()) {
            kelasBelajar = pelatihanUser.get(0).getAllKelasBelajar();
            for (Pelatihan pelatihan : pelatihanUser) {
                programCb.getItems().add(pelatihan.getNamaPelatihan());
            }
            programCb.getSelectionModel().selectFirst();

            loadAndDisplayKelasBelajar();

            programCb.setOnAction(event -> {
                String selectedProgram = programCb.getValue();
                for (Pelatihan pelatihan : pelatihanUser) {
                    if (pelatihan.getNamaPelatihan().equals(selectedProgram)) {
                        this.kelasBelajar = pelatihan.getAllKelasBelajar();
                        loadAndDisplayKelasBelajar();
                        break;
                    }
                }
            });
        }
    }

    private void loadAndDisplayKelasBelajar() {
        if (kelasBelajar != null) {
            displayedKelasBelajar.clear();
            displayedKelasBelajar.addAll(kelasBelajar);
            displayKelasBelajar(displayedKelasBelajar);
        }
    }

    private void displayKelasBelajar(List<KelasBelajar> kelasList) {
        vboxMateri.getChildren().clear();
        HBox row = null;
        int kelasCount = 0;
        vboxMateri.setAlignment(Pos.TOP_LEFT);

        for (KelasBelajar kelas : kelasList) {
            if (kelasCount % 7 == 0) {
                row = new HBox(20);
                VBox.setMargin(row, new Insets(0, 0, 10, 0));
                vboxMateri.getChildren().add(row);
            }
            kelasCount++;

            VBox vbox = createKelasUI(kelas);
            row.getChildren().add(vbox);
        }
    }

    private VBox createKelasUI(KelasBelajar kelas) {
        int idKelas = kelas.getId();
        String namaKelas = kelas.getNamaKelas();

        VBox vbox = new VBox();
        vbox.setMinHeight(60.0);
        vbox.setMinWidth(40.0);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        
        VBox kotakKelas = new VBox();
        kotakKelas.setMinHeight(40.0);
        kotakKelas.setMinWidth(40.0);
        kotakKelas.setAlignment(Pos.CENTER);

        BackgroundFill backgroundFill = new BackgroundFill(
            Color.web("#FFD983"),
            new CornerRadii(5),
            Insets.EMPTY
        );
        kotakKelas.setBackground(new Background(backgroundFill));

        ImageView gambarKelas = new ImageView(new Image("file:///"+getImageFilePath(kelas.getGambarKelas())));
        gambarKelas.setFitHeight(20);
        gambarKelas.setFitWidth(20);
        
        kotakKelas.getChildren().add(gambarKelas);

        Text namaKelasText = new Text(namaKelas);
        namaKelasText.setStyle("-fx-font-weight: bold;");
        
        vbox.setOnMouseClicked(action -> handleOpenKelas(kelas));

        vbox.getChildren().addAll(kotakKelas, namaKelasText);

        return vbox;
    }
    
    private void handleOpenKelas(KelasBelajar kelasBelajar) {
    	try {
			App.showKelasBelajarView(kelasBelajar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void handleKelasPraktik() {
    	try {
			App.showKelasPraktikView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void handleKelasOnline() {
    	try {
			App.showKelasOnlineView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private String getImageFilePath(String relativePath) {
        String projectPath = System.getProperty("user.dir");
        String absolutePath = projectPath + "/src/".replace("/", File.separator) + relativePath.replace("/", File.separator);
        return absolutePath;
    }
}
