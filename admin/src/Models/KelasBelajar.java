package Models;

import Service.repository.KelasRepository;

import java.util.ArrayList;

public class KelasBelajar {
    private int id, idPelatihan;
    private String namaKelas, gambarKelas;

    public KelasBelajar(int id, int idPelatihan, String namaKelas, String gambarKelas) {
        this.id = id;
        this.idPelatihan = idPelatihan;
        this.namaKelas = namaKelas;
        this.gambarKelas = gambarKelas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPelatihan() {
        return idPelatihan;
    }

    public void setIdPelatihan(int idPelatihan) {
        this.idPelatihan = idPelatihan;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getGambarKelas() {
        return gambarKelas;
    }

    public void setGambarKelas(String gambarKelas) {
        this.gambarKelas = gambarKelas;
    }

//    getVideos
    public ArrayList<KelasVideo> getVideos() {
        return KelasRepository.getVideosByIdKelas(this.id);
    }

//    getQuizzes
    public ArrayList<KelasQuiz> getQuizzes() {
        return KelasRepository.getQuizzesByIdKelas(this.id);
    }
}
