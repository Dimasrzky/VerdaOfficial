package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Models.Komunitas;
import Models.LanggananPengguna;
import Models.Lowongan;
import Models.LowonganPengguna;
import Models.Pelatihan;
import Models.PelatihanPengguna;
import Models.Pengguna;
import Service.repository.KomunitasRepository;
import Service.repository.LanggananPenggunaRepository;
import Service.repository.LowonganPenggunaRepository;
import Service.repository.LowonganRepository;
import Service.repository.PelatihanRepository;
import Service.repository.PenggunaRepository;
import Service.repository.PelatihanPenggunaRepository;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.text.Text;

public class BerandaController {

	@FXML
	private Text jumlahPengguna;
	
	@FXML
	private Text jumlahLowongan;
	
	@FXML
	private Text jumlahProgram;
	
	@FXML
	private Text jumlahKomunitas;
	
	@FXML
	private Text jumlahPelatihanPengguna;
	
	@FXML
	private Text jumlahLowonganPengguna;
	
	@FXML
	private Text jumlahLanggananPengguna;
	
	@FXML
	private PieChart chartLanggananPengguna;
	
	@FXML
	private PieChart chartPelatihanPengguna;
	
	private ArrayList<Pengguna> daftarPengguna;
	private ArrayList<Lowongan> daftarLowongan;
	private ArrayList<Pelatihan> daftarPelatihan;
	private ArrayList<Komunitas> daftarKomunitas;
	private ArrayList<PelatihanPengguna> daftarPelatihanPengguna;
	private ArrayList<LowonganPengguna> daftarLowonganPengguna;
	private ArrayList<LanggananPengguna> daftarLanggananPengguna;
	
	@FXML
	public void initialize() {
		daftarPengguna = PenggunaRepository.getAllPenggunaDB();
		daftarLowongan = LowonganRepository.getAllLowonganDB();
		daftarPelatihan = PelatihanRepository.getAllPelatihanDB();
		daftarKomunitas = KomunitasRepository.getAllKomunitas();
		daftarPelatihanPengguna = PelatihanPenggunaRepository.getAllPelatihanPengguna();
		daftarLowonganPengguna = LowonganPenggunaRepository.getAllLowonganPengguna();
		daftarLanggananPengguna = LanggananPenggunaRepository.getAllLanggananPengguna();
		
		jumlahPengguna.setText(Integer.toString(daftarPengguna.size()));
		jumlahLowongan.setText(Integer.toString(daftarLowongan.size()));
		jumlahProgram.setText(Integer.toString(daftarPelatihan.size()));
		jumlahKomunitas.setText(Integer.toString(daftarKomunitas.size()));
		jumlahPelatihanPengguna.setText(Integer.toString(daftarPelatihanPengguna.size()));
		jumlahLowonganPengguna.setText(Integer.toString(daftarLowonganPengguna.size()));
		jumlahLanggananPengguna.setText(Integer.toString(daftarLanggananPengguna.size()));
		
        Map<String, Integer> jenisLanggananCount = new HashMap<>();
        for (LanggananPengguna langgananPengguna : daftarLanggananPengguna) {
            String jenisLangganan = langgananPengguna.getLangganan().getJenisLangganan();
            jenisLanggananCount.put(jenisLangganan, jenisLanggananCount.getOrDefault(jenisLangganan, 0) + 1);
        }

        for (String jenis : jenisLanggananCount.keySet()) {
            int jumlah = jenisLanggananCount.get(jenis);
            PieChart.Data data = new PieChart.Data(jenis + " (" + jumlah + ")", jumlah);
            chartLanggananPengguna.getData().add(data);
        }
        
        chartLanggananPengguna.setTitle("Langganan Pengguna");
        
        
        Map<String, Integer> jenisPelatihanCount = new HashMap<>();
        for (PelatihanPengguna pelatihanPengguna : daftarPelatihanPengguna) {
            String jenisPelatihan = pelatihanPengguna.getInfoPelatihan().getNamaPelatihan();
            jenisPelatihanCount.put(jenisPelatihan, jenisPelatihanCount.getOrDefault(jenisPelatihan, 0) + 1);
        }

        for (String jenis : jenisPelatihanCount.keySet()) {
            int jumlah = jenisPelatihanCount.get(jenis);
            PieChart.Data data = new PieChart.Data(jenis + " (" + jumlah + ")", jumlah);
            chartPelatihanPengguna.getData().add(data);
        }
        
        chartPelatihanPengguna.setTitle("Pelatihan Pengguna");
	}
}
