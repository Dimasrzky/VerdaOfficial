package Models;

import Service.repository.LowonganRepository;

import java.util.ArrayList;

public class Lowongan {
    private String posisi;
    private String perusahaan;
    private String lokasi;
    private int gaji;
    private String kualifikasi;
    private String waktuDiposting;
    private String jenisWaktu;
    private String jobDesk;
    private String tanggungJawab;
    private int id;

    // Constructor, getters, and setters
    public Lowongan(String posisi, String perusahaan, String lokasi, int gaji, String kualifikasi, String waktuDiposting, String jenisWaktu, String jobDesk, String tanggungJawab) {
        this.posisi = posisi;
        this.perusahaan = perusahaan;
        this.lokasi = lokasi;
        this.gaji = gaji;
        this.kualifikasi = kualifikasi;
        this.waktuDiposting = waktuDiposting;
        this.jenisWaktu = jenisWaktu;
        this.jobDesk = jobDesk;
        this.tanggungJawab = tanggungJawab;
    }

    // Getters and setters
    public String getPosisi() { return posisi; }
    public void setPosisi(String posisi) { this.posisi = posisi; }

    public String getPerusahaan() { return perusahaan; }
    public void setPerusahaan(String perusahaan) { this.perusahaan = perusahaan; }

    public String getLokasi() { return lokasi; }
    public void setLokasi(String lokasi) { this.lokasi = lokasi; }

    public int getGaji() { return gaji; }
    public void setGaji(int gaji) { this.gaji = gaji; }

    public String getKualifikasi() { return kualifikasi; }
    public void setKualifikasi(String kualifikasi) { this.kualifikasi = kualifikasi; }

    public String getWaktuDiposting() { return waktuDiposting; }
    public void setWaktuDiposting(String waktuDiposting) { this.waktuDiposting = waktuDiposting; }

    public String getJenisWaktu() { return jenisWaktu; }
    public void setJenisWaktu(String jenisWaktu) { this.jenisWaktu = jenisWaktu; }

    public String getJobDesk() {
        return jobDesk;
    }

    public void setJobDesk(String jobDesk) {
        this.jobDesk = jobDesk;
    }

    public String getTanggungJawab() {
        return tanggungJawab;
    }

    public void setTanggungJawab(String tanggungJawab) {
        this.tanggungJawab = tanggungJawab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<Lowongan> getAllLowongn(){
        return LowonganRepository.getAllLowonganDB();
    }

    public boolean deleteLowongan(){
        return LowonganRepository.deleteLowongan(this.id);
    }
}
