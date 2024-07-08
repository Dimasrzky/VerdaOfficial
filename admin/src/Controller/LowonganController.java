package Controller;

import Models.Lowongan;
import Models.LowonganPengguna;
import Service.repository.LowonganPenggunaRepository;
import Service.repository.LowonganRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.ArrayList;

import Main.App;

public class LowonganController {
	@FXML
	private Button tambahButton;
	
    @FXML
    private TableView<LowonganPengguna> tableLamaran;
    @FXML
    private TableColumn<LowonganPengguna, Integer> lamaran_idColumn;
    @FXML
    private TableColumn<LowonganPengguna, Integer> lamaran_statusColumn;
    @FXML
    private TableColumn<LowonganPengguna, String> lamaran_namaPenggunaColumn;
    @FXML
    private TableColumn<LowonganPengguna, String> lamaran_genderColumn;
    @FXML
    private TableColumn<LowonganPengguna, String> lamaran_asalColumn;
    @FXML
    private TableColumn<LowonganPengguna, String> lamaran_pendidikanColumn;
    @FXML
    private TableColumn<LowonganPengguna, String> lamaran_tentangColumn;
    @FXML
    private TableColumn<LowonganPengguna, Void> lamaran_aksiColumn;

    @FXML
    private TableView<Lowongan> tableLowongan;
    @FXML
    private TableColumn<Lowongan, Integer> lowongan_idColumn;
    @FXML
    private TableColumn<Lowongan, String> lowongan_posisiColumn;
    @FXML
    private TableColumn<Lowongan, String> lowongan_perusahaanColumn;
    @FXML
    private TableColumn<Lowongan, String> lowongan_lokasiColumn;
    @FXML
    private TableColumn<Lowongan, Double> lowongan_gajiColumn;
    @FXML
    private TableColumn<Lowongan, String> lowongan_kualifikasiColumn;
    @FXML
    private TableColumn<Lowongan, String> lowongan_jenisWaktuColumn;
    @FXML
    private TableColumn<Lowongan, String> lowongan_deskripsiColumn;
    @FXML
    private TableColumn<Lowongan, String> lowongan_tanggungJawabColumn;
    @FXML
    private TableColumn<Lowongan, String> lowongan_waktuDipostingColumn;
    @FXML
    private TableColumn<Lowongan, Void> lowongan_aksiColumn;

    private ObservableList<LowonganPengguna> daftarLowonganPengguna;
    private ObservableList<Lowongan> daftarLowongan;

    @FXML
    public void initialize() {
        loadTableLamaran();
        loadTableLowongan();
        
        tambahButton.setOnMouseClicked(action -> handleTambahLowongan());
    }

    private void handleTambahLowongan() {
    	try {
			App.showTambahLowonganView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void loadTableLamaran() {
        ArrayList<LowonganPengguna> data = LowonganPenggunaRepository.getAllLowonganPengguna();
        daftarLowonganPengguna = FXCollections.observableArrayList(data);

        lamaran_idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lamaran_statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        lamaran_namaPenggunaColumn.setCellValueFactory(new PropertyValueFactory<>("namaLengkap"));
        lamaran_genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        lamaran_asalColumn.setCellValueFactory(new PropertyValueFactory<>("asalDaerah"));
        lamaran_pendidikanColumn.setCellValueFactory(new PropertyValueFactory<>("pendidikanTerakhir"));
        lamaran_tentangColumn.setCellValueFactory(new PropertyValueFactory<>("tentangSaya"));

        lamaran_aksiColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<LowonganPengguna, Void> call(final TableColumn<LowonganPengguna, Void> param) {
                final TableCell<LowonganPengguna, Void> cell = new TableCell<>() {
                    private final Button terimaButton = new Button("Terima");
                    private final Button tolakButton = new Button("Tolak");
//                    private final Button cekSertifikatButton = new Button("Cek Sertifikat");

                    {
                    	terimaButton.getStyleClass().add("terima-button");
                    	tolakButton.getStyleClass().add("tolak-button");
//                    	cekSertifikatButton.getStyleClass().add("cekSertifikat-button");
                    	
                        terimaButton.setOnAction(event -> {
                            LowonganPengguna selected = getTableView().getItems().get(getIndex());
                            if (selected != null) {
                            	Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Apakah Anda yakin ingin menerima lamaran ini?", ButtonType.YES, ButtonType.NO);
                                alert.showAndWait().ifPresent(response -> {
                                    if (response == ButtonType.YES) {
                                        LowonganRepository.acceptStatusDB(selected.getId());
                                        loadTableLamaran();
                                    }
                                });
                                
                            }
                        });

                        tolakButton.setOnAction(event -> {
                            LowonganPengguna selected = getTableView().getItems().get(getIndex());
                            if (selected != null) {
                            	Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Apakah Anda yakin ingin menolak lamaran ini?", ButtonType.YES, ButtonType.NO);
                                alert.showAndWait().ifPresent(response -> {
                                    if (response == ButtonType.YES) {
                                    	LowonganRepository.rejectStatusDB(selected.getId());
                                    	loadTableLamaran();
                                    }
                                });
                            }
                        });

//                        cekSertifikatButton.setOnAction(event -> {
//                            LowonganPengguna selected = getTableView().getItems().get(getIndex());
//                            if (selected != null) {
//                                System.out.println("Cek Sertifikat: " + selected.getId());
//                            }
//                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
//                            VBox buttons = new VBox(terimaButton, tolakButton, cekSertifikatButton);
                            VBox buttons = new VBox(terimaButton, tolakButton);
                            buttons.setAlignment(Pos.CENTER);
                            buttons.setSpacing(5);
                            setGraphic(buttons);
                        }
                    }
                };
                return cell;
            }
        });

        tableLamaran.setItems(daftarLowonganPengguna);
    }

    private void loadTableLowongan() {
        ArrayList<Lowongan> data = LowonganRepository.getAllLowonganDB();
        daftarLowongan = FXCollections.observableArrayList(data);

        lowongan_idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lowongan_posisiColumn.setCellValueFactory(new PropertyValueFactory<>("posisi"));
        lowongan_perusahaanColumn.setCellValueFactory(new PropertyValueFactory<>("perusahaan"));
        lowongan_lokasiColumn.setCellValueFactory(new PropertyValueFactory<>("lokasi"));
        lowongan_gajiColumn.setCellValueFactory(new PropertyValueFactory<>("gaji"));
        lowongan_kualifikasiColumn.setCellValueFactory(new PropertyValueFactory<>("kualifikasi"));
        lowongan_jenisWaktuColumn.setCellValueFactory(new PropertyValueFactory<>("jenisWaktu"));
        lowongan_deskripsiColumn.setCellValueFactory(new PropertyValueFactory<>("jobDesk"));
        lowongan_tanggungJawabColumn.setCellValueFactory(new PropertyValueFactory<>("tanggungJawab"));
        lowongan_waktuDipostingColumn.setCellValueFactory(new PropertyValueFactory<>("waktuDiposting"));

        lowongan_aksiColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Lowongan, Void> call(final TableColumn<Lowongan, Void> param) {
                final TableCell<Lowongan, Void> cell = new TableCell<>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                    	deleteButton.getStyleClass().add("delete-button");
                        deleteButton.setOnAction(event -> {
                            Lowongan selected = getTableView().getItems().get(getIndex());
                            if (selected != null) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Apakah Anda yakin ingin menghapus data?", ButtonType.YES, ButtonType.NO);
                                alert.showAndWait().ifPresent(response -> {
                                    if (response == ButtonType.YES) {
                                        LowonganRepository.deleteLowongan(selected.getId());
                                        daftarLowongan.remove(selected);
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
                return cell;
            }
        });

        tableLowongan.setItems(daftarLowongan);
    }
}
