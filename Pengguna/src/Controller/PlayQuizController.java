package Controller;

import Main.App;
import Models.KelasBelajar;
import Models.KelasPertanyaan;
import Models.KelasQuiz;
import Models.KelasJawaban;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PlayQuizController {
    @FXML
    private VBox quizBox;

    private KelasBelajar kelasBelajar;
    private KelasQuiz kelasQuiz;
    private LinkedList<KelasPertanyaan> pertanyaanList;
    private int currentQuestionIndex = 0;
    private ToggleGroup toggleGroup = new ToggleGroup();

    private Map<Integer, Integer> userAnswers = new HashMap<>();

    public void initData(KelasBelajar kelasBelajar, KelasQuiz kelasQuiz) {
        this.kelasBelajar = kelasBelajar;
        this.kelasQuiz = kelasQuiz;
        this.pertanyaanList = new LinkedList<>(kelasQuiz.getPertanyaans());
        displayQuestion();
    }

    private void displayQuestion() {
        quizBox.getChildren().clear();
        toggleGroup = new ToggleGroup();

        if (currentQuestionIndex < pertanyaanList.size()) {
            KelasPertanyaan currentQuestion = pertanyaanList.get(currentQuestionIndex);

            Text questionNumber = new Text("Pertanyaan " + (currentQuestionIndex + 1) + " / " + pertanyaanList.size());
            Text questionText = new Text(currentQuestion.getPertanyaan());
            questionText.setWrappingWidth(535);

            VBox answersBox = new VBox();
            answersBox.setSpacing(5.0);
            VBox.setMargin(answersBox, new Insets(10, 0, 0, 0));
            
            for (KelasJawaban jawaban : currentQuestion.getJawabans()) {
                RadioButton answerButton = new RadioButton(jawaban.getJawaban());
                answerButton.getStyleClass().add("answerButton");
                answerButton.setToggleGroup(toggleGroup);
                answerButton.setUserData(jawaban.getId());
                answersBox.getChildren().add(answerButton);

                if (userAnswers.containsKey(currentQuestion.getId()) && userAnswers.get(currentQuestion.getId()) == jawaban.getId()) {
                    answerButton.setSelected(true);
                }
            }

            Button previousButton = new Button("Previous");
            previousButton.setOnAction(e -> {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    displayQuestion();
                }
            });
            if (currentQuestionIndex == 0) {
                previousButton.setVisible(false);
            } else {
                previousButton.setVisible(true);
            }

            Button nextButton = new Button(currentQuestionIndex == pertanyaanList.size() - 1 ? "Submit" : "Next");
            nextButton.setOnAction(e -> {
                Toggle selectedToggle = toggleGroup.getSelectedToggle();
                if (selectedToggle != null) {
                    userAnswers.put(currentQuestion.getId(), (Integer) selectedToggle.getUserData());
                }
                if (currentQuestionIndex == pertanyaanList.size() - 1) {
                    submitQuiz();
                } else {
                    currentQuestionIndex++;
                    displayQuestion();
                }
            });
            
            HBox buttons = new HBox();
            Region r = new Region();
            HBox.setHgrow(r, Priority.ALWAYS);
            buttons.getChildren().addAll(previousButton, r, nextButton);
            
            VBox.setVgrow(answersBox, Priority.ALWAYS);
            
            questionNumber.getStyleClass().add("questionNumber");
            questionText.getStyleClass().add("questionText");
            previousButton.getStyleClass().add("previousButton");
            nextButton.getStyleClass().add("nextButton");

            quizBox.getChildren().addAll(questionNumber, questionText, answersBox, buttons);
        }
    }

    private void submitQuiz() {
        quizBox.getChildren().clear();

        int correctAnswers = 0;
        for (KelasPertanyaan pertanyaan : pertanyaanList) {
            List<KelasJawaban> correctJawabans = pertanyaan.getJawabans().stream().filter(KelasJawaban::isTrue).toList();
            if (userAnswers.containsKey(pertanyaan.getId())) {
                int selectedJawabanId = userAnswers.get(pertanyaan.getId());
                for (KelasJawaban jawaban : correctJawabans) {
                    if (jawaban.getId() == selectedJawabanId) {
                        correctAnswers++;
                        break;
                    }
                }
            }
        }

        Text scoreHeadText = new Text("Anda mendapatkan skor ");
        Text scoreText = new Text(Integer.toString((correctAnswers * 100) / pertanyaanList.size()));
        Text scoreValue = new Text(correctAnswers + " dari " + pertanyaanList.size() + " pertanyaan dijawab benar.");
        Button homeButton = new Button("Kembali");
        
        homeButton.setOnAction(e -> {
            try {
                App.showKelasBelajarView(kelasBelajar);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        
        VBox.setMargin(homeButton, new Insets(20, 0, 0, 0));
        scoreText.setFill(Color.web("#03CBB3"));
        
        scoreHeadText.getStyleClass().add("scoreHeadText");
        scoreText.getStyleClass().add("scoreText");
        scoreValue.getStyleClass().add("scoreValue");
        homeButton.getStyleClass().add("nextButton");
        
        VBox hasil = new VBox();
        hasil.getChildren().addAll(scoreHeadText, scoreText, scoreValue, homeButton);
        
        VBox.setVgrow(hasil, Priority.ALWAYS);
        HBox.setHgrow(hasil, Priority.ALWAYS);
        
        hasil.setSpacing(6);
        hasil.setAlignment(Pos.CENTER);

        quizBox.getChildren().addAll(hasil);
    }

    @FXML
    private void handleBackButtonClick(MouseEvent event) {
        try {
            App.showKelasBelajarView(kelasBelajar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
