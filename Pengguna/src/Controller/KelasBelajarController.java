package Controller;

import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;

import Main.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.Session;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import Models.KelasBelajar;
import Models.KelasQuiz;
import Models.KelasVideo;
import Models.Langganan;
import Models.Pengguna;

public class KelasBelajarController {
	
	@FXML
	private ImageView gambarKelas;
	
	@FXML
	private Text namaKelas;
	

    @FXML
    private VBox vboxdaftarmateri;

    Langganan userLangganan;
    private KelasBelajar kelasBelajar;
    private PriorityQueue<Object> materiQueue;

    public void initData(KelasBelajar kelasBelajar) {
        this.kelasBelajar = kelasBelajar;
        Pengguna currentUser = Session.getUser();
        userLangganan = currentUser.getLangganan();
        
        namaKelas.setText(kelasBelajar.getNamaKelas());
        gambarKelas.setImage(new Image("file:///"+getImageFilePath(kelasBelajar.getGambarKelas())));

        materiQueue = new PriorityQueue<>((o1, o2) -> {
            int order1 = (o1 instanceof KelasVideo) ? ((KelasVideo) o1).getOrderVideo() : ((KelasQuiz) o1).getOrderQuiz();
            int order2 = (o2 instanceof KelasVideo) ? ((KelasVideo) o2).getOrderVideo() : ((KelasQuiz) o2).getOrderQuiz();
            return Integer.compare(order1, order2);
        });

        materiQueue.addAll(kelasBelajar.getVideos());
        materiQueue.addAll(kelasBelajar.getQuizzes());

        processMateri();
    }

    private void processMateri() {
        while (!materiQueue.isEmpty()) {
            Object item = materiQueue.poll();

            try {
                HBox hbox;
                if (item instanceof KelasVideo) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/MateriItem.fxml"));
                    hbox = loader.load();
                    MateriItemController controller = loader.getController();
                    controller.setData((KelasVideo) item);

                    int order = ((KelasVideo) item).getOrderVideo();
                    if (order >= 2 && userLangganan == null) {
                        Pane overlay = new Pane();
                        overlay.getStyleClass().add("overlay");
                        hbox.getChildren().add(overlay);
                        hbox.getStyleClass().add("disable-click");
                    } else {
                        hbox.setOnMouseClicked(event -> handleOpenVideo((KelasVideo) item));
                    }
                } else if (item instanceof KelasQuiz) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/KuisItem.fxml"));
                    hbox = loader.load();
                    KuisItemController controller = loader.getController();
                    controller.setData((KelasQuiz) item);

                    int order = ((KelasQuiz) item).getOrderQuiz();
                    if (order >= 2 && userLangganan == null) {
                        Pane overlay = new Pane();
                        overlay.getStyleClass().add("overlay");
                        hbox.getChildren().add(overlay);
                        hbox.getStyleClass().add("disable-click");
                    } else {
                        hbox.setOnMouseClicked(event -> handleOpenQuiz((KelasQuiz) item));
                    }
                } else {
                    continue;
                }

                vboxdaftarmateri.getChildren().add(hbox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleBackButtonClick(MouseEvent event) throws Exception {
        App.showPelatihanView();
    }

    private void handleOpenVideo(KelasVideo video) {
        try {
            App.showPlayVideoView(kelasBelajar, video);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleOpenQuiz(KelasQuiz quiz) {
        try {
            App.showPlayQuizView(kelasBelajar, quiz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String getImageFilePath(String relativePath) {
        String projectPath = System.getProperty("user.dir");
        String absolutePath = projectPath + "/src/".replace("/", File.separator) + relativePath.replace("/", File.separator);
        return absolutePath;
    }
}
