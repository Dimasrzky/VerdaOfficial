package Models;

import Service.repository.LanggananPenggunaRepository;
import Service.repository.LanggananRepository;

public class Langganan {
    private int id, hargaLangganan;
    private String jenisLangganan, durasiLangganan, benefitLangganan;

    public Langganan(int id, String jenisLangganan, String durasiLangganan, String benefitLangganan, int hargaLangganan) {
        this.id = id;
        this.jenisLangganan = jenisLangganan;
        this.durasiLangganan = durasiLangganan;
        this.benefitLangganan = benefitLangganan;
        this.hargaLangganan = hargaLangganan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHargaLangganan() {
        return hargaLangganan;
    }

    public void setHargaLangganan(int hargaLangganan) {
        this.hargaLangganan = hargaLangganan;
    }

    public String getJenisLangganan() {
        return jenisLangganan;
    }

    public void setJenisLangganan(String jenisLangganan) {
        this.jenisLangganan = jenisLangganan;
    }

    public String getDurasiLangganan() {
        return durasiLangganan;
    }

    public void setDurasiLangganan(String durasiLangganan) {
        this.durasiLangganan = durasiLangganan;
    }

    public String getBenefitLangganan() {
        return benefitLangganan;
    }

    public void setBenefitLangganan(String benefitLangganan) {
        this.benefitLangganan = benefitLangganan;
    }

    public boolean joinLangganan (int idPengguna){
        return LanggananPenggunaRepository.addLangganan(idPengguna, this.id);
    }

    public boolean cancelLangganan (int idPengguna){
        return LanggananPenggunaRepository.deleteLangganan(idPengguna, this.id);
    }
}
