package Models;

import Service.repository.*;

import java.util.ArrayList;

public class Admin {
    private String username, email, password, profile_picture;
    private int id;

    public Admin(int id, String username, String email, String password, String profile_picture) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile_picture = profile_picture;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static ArrayList<PelatihanPengguna> getAllPelatihanPengguna(){
        return PelatihanPenggunaRepository.getAllPelatihanPengguna();
    }

    public static ArrayList<LowonganPengguna> getAllLowonganPengguna(){
        return LowonganRepository.getAllLowonganPenggunaDB();
    }

    public static boolean tambahLangganan(int idPengguna, int idLangganan){
        return LanggananPenggunaRepository.addLangganan(idPengguna, idLangganan);
    }

    public static boolean hapusLangganan(int idPengguna, int idLangganan){
        return LanggananPenggunaRepository.deleteLangganan(idPengguna, idLangganan);
    }

    public boolean joinPelatihan(int idPelatihan, int idPengguna){
        return PelatihanRepository.joinPelatihanDB(idPelatihan, idPengguna);
    }

    public boolean hapusPelatihan(int idPelatihan, int idPengguna){
        return PelatihanRepository.deletePelatihanDB(idPelatihan, idPengguna);
    }
}
