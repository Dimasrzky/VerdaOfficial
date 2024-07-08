package Controller;

import java.util.ArrayList;

import Main.App;
import Models.Komunitas;
import Service.repository.KomunitasRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class KomunitasController {
	@FXML
    private TableView<Komunitas> table;
    @FXML
    private TableColumn<Komunitas, Integer> idColumn;
    @FXML
    private TableColumn<Komunitas, String> namaKomunitasColumn;
    @FXML
    private TableColumn<Komunitas, String> kategoriColumn;
    @FXML
    private TableColumn<Komunitas, String> visibilityColumn;
    @FXML
    private TableColumn<Komunitas, Void> aksiColumn;
    @FXML
    private Button tambahButton;

    private ObservableList<Komunitas> daftarKomunitas;
    
    @FXML
    public void initialize() {
        tambahButton.getStyleClass().add("tambah-button");
        loadTableData();
        
        tambahButton.setOnMouseClicked(action -> handleTambahKomunitas());
    }

    private void handleTambahKomunitas() {
    	try {
			App.showTambahKomunitasView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void loadTableData() {
        ArrayList<Komunitas> data = KomunitasRepository.getAllKomunitas();
        daftarKomunitas = FXCollections.observableArrayList(data);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        namaKomunitasColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kategoriColumn.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        visibilityColumn.setCellValueFactory(new PropertyValueFactory<>("visibility"));


        idColumn.setStyle("-fx-alignment: CENTER;");
        namaKomunitasColumn.setStyle("-fx-alignment: CENTER;");
        kategoriColumn.setStyle("-fx-alignment: CENTER;");
        visibilityColumn.setStyle("-fx-alignment: CENTER;");

        aksiColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Komunitas, Void> call(final TableColumn<Komunitas, Void> param) {
                final TableCell<Komunitas, Void> cell = new TableCell<>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.getStyleClass().add("delete-button");
                        deleteButton.setOnAction(event -> {
                        	Komunitas selectedKomunitas = getTableView().getItems().get(getIndex());
                            if (selectedKomunitas != null) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Apakah anda yakin ingin menghapus data?", ButtonType.YES, ButtonType.NO);
                                alert.showAndWait().ifPresent(response -> {
                                    if (response == ButtonType.YES) {
                                        KomunitasRepository.deleteKomunitas(selectedKomunitas.getId());
                                        daftarKomunitas.remove(selectedKomunitas);
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

        table.setItems(daftarKomunitas);
    }
}
