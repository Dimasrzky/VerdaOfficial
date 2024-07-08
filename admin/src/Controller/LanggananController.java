package Controller;

import Models.LanggananPengguna;
import Service.repository.LanggananPenggunaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;

import Main.App;

public class LanggananController {
    @FXML
    private TableView<LanggananPengguna> table;
    @FXML
    private TableColumn<LanggananPengguna, Integer> idColumn;
    @FXML
    private TableColumn<LanggananPengguna, Integer> idPenggunaColumn;
    @FXML
    private TableColumn<LanggananPengguna, String> paketLanggananColumn;
    @FXML
    private TableColumn<LanggananPengguna, String> aktifHinggaColumn;
    @FXML
    private TableColumn<LanggananPengguna, Void> aksiColumn;
    @FXML
    private Button tambahButton;

    private ObservableList<LanggananPengguna> daftarLanggananPengguna;

    @FXML
    public void initialize() {
        tambahButton.getStyleClass().add("tambah-button");
        loadTableData();
        tambahButton.setOnMouseClicked(action -> handleTambahLangganan());
    }

    private void handleTambahLangganan() {
    	try {
			App.showTambahLanggananView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void loadTableData() {
        ArrayList<LanggananPengguna> data = LanggananPenggunaRepository.getAllLanggananPengguna();
        daftarLanggananPengguna = FXCollections.observableArrayList(data);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idPenggunaColumn.setCellValueFactory(new PropertyValueFactory<>("idPengguna"));
        aktifHinggaColumn.setCellValueFactory(new PropertyValueFactory<>("aktifHingga"));

        paketLanggananColumn.setCellValueFactory(cellData -> {
            LanggananPengguna lp = cellData.getValue();
            String jenisLangganan = lp.getLangganan().getJenisLangganan();
            return new javafx.beans.property.SimpleStringProperty(jenisLangganan);
        });

        idColumn.setStyle("-fx-alignment: CENTER;");
        idPenggunaColumn.setStyle("-fx-alignment: CENTER;");
        paketLanggananColumn.setStyle("-fx-alignment: CENTER;");
        aktifHinggaColumn.setStyle("-fx-alignment: CENTER;");

        aksiColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<LanggananPengguna, Void> call(final TableColumn<LanggananPengguna, Void> param) {
                final TableCell<LanggananPengguna, Void> cell = new TableCell<>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.getStyleClass().add("delete-button");
                        deleteButton.setOnAction(event -> {
                            LanggananPengguna selectedLanggananPengguna = getTableView().getItems().get(getIndex());
                            if (selectedLanggananPengguna != null) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Apakah anda yakin ingin menghapus data?", ButtonType.YES, ButtonType.NO);
                                alert.showAndWait().ifPresent(response -> {
                                    if (response == ButtonType.YES) {
                                        LanggananPenggunaRepository.deleteLangganan(selectedLanggananPengguna.getIdPengguna(), selectedLanggananPengguna.getLangganan().getId());
                                        daftarLanggananPengguna.remove(selectedLanggananPengguna);
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

        table.setItems(daftarLanggananPengguna);
    }
}
