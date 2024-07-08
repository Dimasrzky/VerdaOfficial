package Models;

import Service.repository.KelasRepository;

import java.util.ArrayList;

public class KelasPertanyaan {
    private int id, idQuiz;
    private String pertanyaan;

    public KelasPertanyaan(int id, int idQuiz, String pertanyaan) {
        this.id = id;
        this.idQuiz = idQuiz;
        this.pertanyaan = pertanyaan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public ArrayList<KelasJawaban> getJawabans() {
        return KelasRepository.getJawabanByIdPertanyaan(this.id);
    }
}
