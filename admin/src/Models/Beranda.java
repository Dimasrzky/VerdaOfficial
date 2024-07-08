package Models;

public class Beranda {
    private String pengguna;
    private String berita;

    public Beranda(String pengguna, String berita) {
        this.pengguna = pengguna;
        this.berita = berita;
    }

    public String getPengguna() {
        return pengguna;
    }

    public void setPengguna(String pengguna) {
        this.pengguna = pengguna;
    }

    public String getBerita() {
        return berita;
    }

    public void setBerita(String berita) {
        this.berita = berita;
    }
}
