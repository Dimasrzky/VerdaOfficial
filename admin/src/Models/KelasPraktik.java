package Models;

import Service.repository.KelasRepository;

public class KelasPraktik {
    int id, idPelatihan;
    String judulPraktik;

    public KelasPraktik(int id, int idPelatihan, String judulPraktik) {
        this.id = id;
        this.idPelatihan = idPelatihan;
        this.judulPraktik = judulPraktik;
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

    public String getJudulPraktik() {
        return judulPraktik;
    }

    public void setJudulPraktik(String judulPraktik) {
        this.judulPraktik = judulPraktik;
    }

    public boolean joinKelasPraktik(int idPengguna, String namaLengkap, String tumbuhan, String linkVideo, int fase){
        return KelasRepository.joinKelasPraktikumDB(idPengguna, this.id, namaLengkap, tumbuhan, linkVideo, fase);
    }
}
