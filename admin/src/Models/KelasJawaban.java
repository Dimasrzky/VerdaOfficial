package Models;

import Service.repository.KelasRepository;

public class KelasJawaban {
    private int id, idPertanyaan;
    private String jawaban;
    private boolean isTrue;

    public KelasJawaban(int id, int idPertanyaan, String jawaban, boolean isTrue) {
        this.id = id;
        this.idPertanyaan = idPertanyaan;
        this.jawaban = jawaban;
        this.isTrue = isTrue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPertanyaan() {
        return idPertanyaan;
    }

    public void setIdPertanyaan(int idPertanyaan) {
        this.idPertanyaan = idPertanyaan;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public boolean inputJawaban(int idPengguna){
        return KelasRepository.inputJawaban(idPengguna, this.id);
    }
}
