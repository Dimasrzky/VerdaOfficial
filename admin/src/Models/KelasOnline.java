package Models;

import Service.repository.KelasRepository;

public class KelasOnline {
    private int id, idPelatihan;
    private String judulSeminar, gambarSeminar, waktuTanggalSeminar, pengajar, link;

    public KelasOnline(int id, int idPelatihan, String judulSeminar, String gambarSeminar, String waktuTanggalSeminar, String pengajar, String link) {
        this.id = id;
        this.idPelatihan = idPelatihan;
        this.judulSeminar = judulSeminar;
        this.gambarSeminar = gambarSeminar;
        this.waktuTanggalSeminar = waktuTanggalSeminar;
        this.pengajar = pengajar;
        this.link = link;
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

    public String getJudulSeminar() {
        return judulSeminar;
    }

    public void setJudulSeminar(String judulSeminar) {
        this.judulSeminar = judulSeminar;
    }

    public String getGambarSeminar() {
        return gambarSeminar;
    }

    public void setGambarSeminar(String gambarSeminar) {
        this.gambarSeminar = gambarSeminar;
    }

    public String getWaktuTanggalSeminar() {
        return waktuTanggalSeminar;
    }

    public void setWaktuTanggalSeminar(String waktuTanggalSeminar) {
        this.waktuTanggalSeminar = waktuTanggalSeminar;
    }

    public String getPengajar() {
        return pengajar;
    }

    public void setPengajar(String pengajar) {
        this.pengajar = pengajar;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean joinKelasOnline(int idPengguna){
        return KelasRepository.joinKelasOnline(this.id, idPengguna);
    }
}
