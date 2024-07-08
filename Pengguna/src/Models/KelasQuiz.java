package Models;

import Service.repository.KelasRepository;

import java.util.ArrayList;

public class KelasQuiz {
    private int id, idKelas, orderQuiz;
    private String namaQuiz;

    public KelasQuiz(int id, int idKelas, int orderQuiz, String namaQuiz) {
        this.id = id;
        this.idKelas = idKelas;
        this.orderQuiz = orderQuiz;
        this.namaQuiz = namaQuiz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(int idKelas) {
        this.idKelas = idKelas;
    }

    public int getOrderQuiz() {
        return orderQuiz;
    }

    public void setOrderQuiz(int orderQuiz) {
        this.orderQuiz = orderQuiz;
    }

    public String getNamaQuiz() {
        return namaQuiz;
    }

    public void setNamaQuiz(String namaQuiz) {
        this.namaQuiz = namaQuiz;
    }

//    getSoal
    public ArrayList<KelasPertanyaan> getPertanyaans() {
        return KelasRepository.getPertanyaanByIdQuiz(this.id);
    }
}
