package Controller;

import Models.PelatihanPengguna;
import Service.repository.PelatihanPenggunaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;

import Main.App;

public class PelatihanController {
    @FXML
    private TableView<PelatihanPengguna> table;
    @FXML
    private TableColumn<PelatihanPengguna, Integer> idColumn;
    @FXML
    private TableColumn<PelatihanPengguna, Integer> idPenggunaColumn;
    @FXML
    private TableColumn<PelatihanPengguna, String> programPelatihanColumn;
    @FXML
    private TableColumn<PelatihanPengguna, String> aktifHinggaColumn;
    @FXML
    private TableColumn<PelatihanPengguna, Void> aksiColumn;
    @FXML
    private Button tambahButton;

    private ObservableList<PelatihanPengguna> daftarPelatihanPengguna;

    @FXML
    public void initialize() {
        tambahButton.getStyleClass().add("tambah-button");
        loadTableData();
        
        tambahButton.setOnMouseClicked(action -> handleTambahPelatihan());
    }

    private void handleTambahPelatihan() {
    	try {
			App.showTambahPelatihanView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void loadTableData() {
        ArrayList<PelatihanPengguna> data = PelatihanPenggunaRepository.getAllPelatihanPengguna();
        daftarPelatihanPengguna = FXCollections.observableArrayList(data);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idPenggunaColumn.setCellValueFactory(new PropertyValueFactory<>("idPengguna"));
        aktifHinggaColumn.setCellValueFactory(new PropertyValueFactory<>("aktifHingga"));

        programPelatihanColumn.setCellValueFactory(cellData -> {
        	PelatihanPengguna lp = cellData.getValue();
            String programPelatihan = lp.getInfoPelatihan().getNamaPelatihan();
            return new javafx.beans.property.SimpleStringProperty(programPelatihan);
        });

        idColumn.setStyle("-fx-alignment: CENTER;");
        idPenggunaColumn.setStyle("-fx-alignment: CENTER;");
        programPelatihanColumn.setStyle("-fx-alignment: CENTER;");
        aktifHinggaColumn.setStyle("-fx-alignment: CENTER;");

        aksiColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<PelatihanPengguna, Void> call(final TableColumn<PelatihanPengguna, Void> param) {
                final TableCell<PelatihanPengguna, Void> cell = new TableCell<>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.getStyleClass().add("delete-button");
                        deleteButton.setOnAction(event -> {
                        	PelatihanPengguna selectedPelatihanPengguna = getTableView().getItems().get(getIndex());
                            if (selectedPelatihanPengguna != null) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Apakah anda yakin ingin menghapus data?", ButtonType.YES, ButtonType.NO);
                                alert.showAndWait().ifPresent(response -> {
                                    if (response == ButtonType.YES) {
                                        PelatihanPenggunaRepository.deletePelatihanPengguna(selectedPelatihanPengguna.getIdPelatihan(), selectedPelatihanPengguna.getIdPengguna());
                                        daftarPelatihanPengguna.remove(selectedPelatihanPengguna);
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

        table.setItems(daftarPelatihanPengguna);
    }
}
