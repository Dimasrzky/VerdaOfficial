package Models;

import Service.repository.LowonganRepository;

public class LowonganPengguna {
    private int id, idLowongan, idPengguna;
    private String status, namaLengkap, gender, asalDaerah, pendidikanTerakhir, tentangSaya;

    public LowonganPengguna(int id, int idLowongan, int idPengguna, String status, String namaLengkap, String gender, String asalDaerah, String pendidikanTerakhir, String tentangSaya) {
        this.id = id;
        this.idLowongan = idLowongan;
        this.idPengguna = idPengguna;
        this.status = status;
        this.namaLengkap = namaLengkap;
        this.gender = gender;
        this.asalDaerah = asalDaerah;
        this.pendidikanTerakhir = pendidikanTerakhir;
        this.tentangSaya = tentangSaya;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLowongan() {
        return idLowongan;
    }

    public void setIdLowongan(int idLowongan) {
        this.idLowongan = idLowongan;
    }

    public int getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAsalDaerah() {
        return asalDaerah;
    }

    public void setAsalDaerah(String asalDaerah) {
        this.asalDaerah = asalDaerah;
    }

    public String getPendidikanTerakhir() {
        return pendidikanTerakhir;
    }

    public void setPendidikanTerakhir(String pendidikanTerakhir) {
        this.pendidikanTerakhir = pendidikanTerakhir;
    }

    public String getTentangSaya() {
        return tentangSaya;
    }

    public void setTentangSaya(String tentangSaya) {
        this.tentangSaya = tentangSaya;
    }

//    getSpesificLowongan
    public Lowongan getSpesificLowongan(){
        return LowonganRepository.getSpecificLowonganDB(this.idLowongan);
    }

    public boolean acceptStatus(){
        return LowonganRepository.acceptStatusDB(this.id);
    }

    public boolean rejectStatus(){
        return LowonganRepository.rejectStatusDB(this.id);
    }
}
