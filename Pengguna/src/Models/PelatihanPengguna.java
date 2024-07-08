package Models;

public class PelatihanPengguna {
    private int id, idPelatihan, idPengguna;
    private String status, aktifHingga;

    public PelatihanPengguna(int id, int idPelatihan, int idPengguna, String status, String aktifHingga) {
        this.id = id;
        this.idPelatihan = idPelatihan;
        this.idPengguna = idPengguna;
        this.status = status;
        this.aktifHingga = aktifHingga;
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

    public String getAktifHingga() {
        return aktifHingga;
    }

    public void setAktifHingga(String aktifHingga) {
        this.aktifHingga = aktifHingga;
    }
}
