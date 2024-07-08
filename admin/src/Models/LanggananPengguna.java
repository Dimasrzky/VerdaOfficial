package Models;

import Service.repository.LanggananRepository;
import Service.repository.PenggunaRepository;

public class LanggananPengguna {
    private int id, idPengguna, idLangganan;
    private String aktifHingga;

    public LanggananPengguna(int id, int idPengguna, int idLangganan, String aktifHingga) {
        this.id = id;
        this.idPengguna = idPengguna;
        this.aktifHingga = aktifHingga;
        this.idLangganan = idLangganan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getAktifHingga() {
        return aktifHingga;
    }

    public void setAktifHingga(String aktifHingga) {
        this.aktifHingga = aktifHingga;
    }

    public Pengguna getPengguna() {
        return PenggunaRepository.getSpecificPenggunaDB(this.idPengguna);
    }

    public Langganan getLangganan() {
        return LanggananRepository.getLanggananByIdDB(this.idLangganan);
    }
}
