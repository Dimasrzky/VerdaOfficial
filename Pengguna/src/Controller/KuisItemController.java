package Controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import Models.KelasQuiz;

public class KuisItemController {

    @FXML
    private Text judulKuis;

    public void setData(KelasQuiz kelasQuiz) {
        judulKuis.setText(kelasQuiz.getNamaQuiz());
    }
}
