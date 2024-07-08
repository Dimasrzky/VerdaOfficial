package Controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import Models.KelasVideo;

public class MateriItemController {

    @FXML
    private Text judulMateri;

    public void setData(KelasVideo kelasVideo) {
        judulMateri.setText(kelasVideo.getJudulVideo());
    }
}
