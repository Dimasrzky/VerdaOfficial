package Models;

import Service.repository.KelasRepository;
import Service.repository.PelatihanPenggunaRepository;
import Service.repository.PelatihanRepository;

import java.util.ArrayList;

public class Pelatihan {
    private int id;
    private String namaPelatihan;

    public Pelatihan(int id, String namaPelatihan) {
        this.id = id;
        this.namaPelatihan = namaPelatihan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaPelatihan() {
        return namaPelatihan;
    }

    public void setNamaPelatihan(String namaPelatihan) {
        this.namaPelatihan = namaPelatihan;
    }

    public static ArrayList<Pelatihan> getAllPelatihan(){
        return PelatihanRepository.getAllPelatihanDB();
    }

    public PelatihanPengguna getPelatihanDetail(int userId){
        return PelatihanPenggunaRepository.getSpecificPelatihanPengguna(this.id, userId);
    }

    public ArrayList<KelasBelajar> getAllKelasBelajar(){
        return KelasRepository.getAllKelasBelajarByPelatihanDB(this.id);
    }

    public ArrayList<KelasOnline> getAllKelasOnline(){
        return KelasRepository.getAllKelasOnlineByIdPelatihan(this.id);
    }

    public boolean joinPelatihan(int userId){
        return PelatihanRepository.joinPelatihanDB(this.id, userId);
    }
}
