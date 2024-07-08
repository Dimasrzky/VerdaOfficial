package Models;


import Service.repository.KelasRepository;
import Service.repository.LanggananRepository;
import Service.repository.LowonganRepository;
import Service.repository.PelatihanRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class Pengguna {
    private String  namaPengguna, nomorTelepon, jenisKelamin, tanggalLahir, profesi, provinsi, kota, email, password;
    private int id;

    public Pengguna(String namaPengguna, String nomorTelepon, String jenisKelamin, String tanggalLahir, String profesi, String provinsi, String kota, String email, String password) {
        this.namaPengguna = namaPengguna;
        this.nomorTelepon = nomorTelepon;
        this.jenisKelamin = jenisKelamin;
        this.tanggalLahir = tanggalLahir;
        this.profesi = profesi;
        this.provinsi = provinsi;
        this.kota = kota;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getProfesi() {
        return profesi;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean joinLowongan (int idLowongan, String status, String namaLengkap, String gender, String asalDaerah, String pendidikanTerakhir, String tentangSaya) throws SQLException {
        return LowonganRepository.joinLowonganDB(idLowongan, this.id, status, namaLengkap, gender, asalDaerah, pendidikanTerakhir, tentangSaya);
    }

    public ArrayList<Pelatihan> getAllUserPelatihan(){
        return PelatihanRepository.getAllPelatihanByUserIDDB(this.id);
    }

    public boolean joinPelatihan(int idPelatihan){
        return PelatihanRepository.joinPelatihanDB(idPelatihan, this.id);
    }

    public boolean inputJawaban(int idJawaban){
        return KelasRepository.inputJawaban(idJawaban, this.id);
    }

    public boolean joinKelasOnline(int idKelasOnline){
        return KelasRepository.joinKelasOnline(idKelasOnline, this.id);
    }

    public boolean joinKelasPraktikum(int idKelas, String namaLengkap, String tumbuhan, String linkVideo, int fase){
        return KelasRepository.joinKelasPraktikumDB(idKelas, this.id, namaLengkap, tumbuhan, linkVideo, fase);
    }

    public Langganan getLangganan(){
        return LanggananRepository.getLanggananPengguna(this.id);
    }

    public boolean joinLangganan(int idLangganan){
        return LanggananRepository.addLangganan(this.id, idLangganan);
    }

}
