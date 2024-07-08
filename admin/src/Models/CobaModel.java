package Models;

public class CobaModel {
    private int id;
    private String nama;
    private String nim;

    // Constructor
    public CobaModel(int id, String nama, String nim) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", nama='" + nama + "', nim='" + nim + "'}";
    }
}
