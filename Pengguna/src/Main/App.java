package Main;

import java.util.ArrayList;

import Controller.KelasBelajarController;
import Controller.LamaranPekerjaanController;
import Controller.LowonganDetailController;
import Controller.PlayQuizController;
import Controller.PlayVideoController;
import Models.KelasBelajar;
import Models.KelasQuiz;
import Models.KelasVideo;
import Models.Lowongan;
import Models.Pelatihan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.AuthInterceptor;
import util.Session;

public class App extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	AuthInterceptor.setPrimaryStage(primaryStage);
        App.primaryStage = primaryStage;

        if (Session.getUser() == null) {
            showLoginView();
        } else {
            showBerandaView();
        };
    }

    public static void showLoginView() throws Exception {
    	if (Session.getUser() == null) {
    		Parent loginView = FXMLLoader.load(App.class.getResource("/Views/Login.fxml"));
            Scene loginScene = new Scene(loginView);
            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Login");
            primaryStage.show();
    	} else {
    		showBerandaView();
    	}
    }

    public static void showRegisterView() throws Exception {
    	if (Session.getUser() == null) {
            Parent registerView = FXMLLoader.load(App.class.getResource("/Views/Registrasi.fxml"));
            Scene registerScene = new Scene(registerView);
            primaryStage.setScene(registerScene);
            primaryStage.setTitle("Registrasi");
            primaryStage.show();
        } else {
            showBerandaView();
        }
    }

    public static void showBerandaView() throws Exception {
    	AuthInterceptor.checkSession();
        Parent berandaView = FXMLLoader.load(App.class.getResource("/Views/Beranda.fxml"));
        Scene berandaScene = new Scene(berandaView);
        primaryStage.setScene(berandaScene);
        primaryStage.setTitle("Beranda");
        primaryStage.show();
    }

    public static void showLowonganView() throws Exception {
        if (Session.getUser() == null) {
            showLoginView();
        } else {
            Parent lowonganView = FXMLLoader.load(App.class.getResource("/Views/Lowongan.fxml"));
            Scene lowonganScene = new Scene(lowonganView);
            primaryStage.setScene(lowonganScene);
            primaryStage.setTitle("Lowongan");
            primaryStage.show();
        }
    }

    public static void showPelatihanView() throws Exception {
        if (Session.getUser() == null) {
            showLoginView();
        } else {
            ArrayList<Pelatihan> pelatihanUser = Session.getUser().getAllUserPelatihan();
            if(pelatihanUser.size() > 0) {
            	Parent pelatihanView = FXMLLoader.load(App.class.getResource("/Views/Pelatihan.fxml"));
                Scene pelatihanScene = new Scene(pelatihanView);
                primaryStage.setScene(pelatihanScene);
                primaryStage.setTitle("Pelatihan");
                primaryStage.show();
            } else {
            	Parent pelatihanView = FXMLLoader.load(App.class.getResource("/Views/PelatihanKosong.fxml"));
                Scene pelatihanScene = new Scene(pelatihanView);
                primaryStage.setScene(pelatihanScene);
                primaryStage.setTitle("Pelatihan");
                primaryStage.show();
            }
            
        }
    }
    public static void showDaftarPelatihanView() throws Exception {
      if (Session.getUser() == null) {
          showLoginView();
      } else {
          Parent pelatihanView = FXMLLoader.load(App.class.getResource("/Views/DaftarPelatihan.fxml"));
          Scene pelatihanScene = new Scene(pelatihanView);
          primaryStage.setScene(pelatihanScene);
          primaryStage.setTitle("Daftar Pelatihan");
          primaryStage.show();
      }
  }

    public static void showKomunitasView() throws Exception {
        if (Session.getUser() == null) {
            showLoginView();
        } else {
            Parent komunitasView = FXMLLoader.load(App.class.getResource("/Views/Komunitas.fxml"));
            Scene komunitasScene = new Scene(komunitasView);
            primaryStage.setScene(komunitasScene);
            primaryStage.setTitle("Komunitas");
            primaryStage.show();
        }
    }

    public static void showLanggananView() throws Exception {
        if (Session.getUser() == null) {
            showLoginView();
        } else {
            Parent langgananView = FXMLLoader.load(App.class.getResource("/Views/Langganan.fxml"));
            Scene langgananScene = new Scene(langgananView);
            primaryStage.setScene(langgananScene);
            primaryStage.setTitle("Langganan");
            primaryStage.show();
        }
    }
    public static void showLowonganDetailView(Lowongan lowongan) throws Exception {
    	if (Session.getUser() == null) {
            showLoginView();
        } else {
        	FXMLLoader loader = new FXMLLoader(App.class.getResource("/Views/LowonganDetail.fxml"));
            Parent root = loader.load();
            
            LowonganDetailController controller = loader.getController();
            controller.initData(lowongan);

            primaryStage.setTitle("Detail Lowongan");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
    }
    public static void showLamaranPekerjaanView(Lowongan lowongan) throws Exception {
    	if (Session.getUser() == null) {
            showLoginView();
        } else {
        	FXMLLoader loader = new FXMLLoader(App.class.getResource("/Views/LamaranPekerjaan.fxml"));
            Parent root = loader.load();
            
            LamaranPekerjaanController controller = loader.getController();
            controller.initData(lowongan);
            

            primaryStage.setTitle("Lamaran Pekerjaan");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
    }
    
    public static void showKelasBelajarView(KelasBelajar kelasBelajar) throws Exception {
    	if (Session.getUser() == null) {
            showLoginView();
        } else {
        	FXMLLoader loader = new FXMLLoader(App.class.getResource("/Views/KelasBelajar.fxml"));
            Parent root = loader.load();
            
            KelasBelajarController controller = loader.getController();
            controller.initData(kelasBelajar);
            

            primaryStage.setTitle("KelasBelajar");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
    }
    public static void showKelasPraktikView() throws Exception {
    	 if (Session.getUser() == null) {
             showLoginView();
         } else {
             Parent kelasPraktikView = FXMLLoader.load(App.class.getResource("/Views/KelasPraktik.fxml"));
             Scene kelasPraktikScene = new Scene(kelasPraktikView);
             primaryStage.setScene(kelasPraktikScene);
             primaryStage.setTitle("KelasPraktik");
             primaryStage.show();
         }
    }
    public static void showKelasOnlineView() throws Exception {
   	 if (Session.getUser() == null) {
            showLoginView();
        } else {
            Parent kelasPraktikView = FXMLLoader.load(App.class.getResource("/Views/KelasOnline.fxml"));
            Scene kelasPraktikScene = new Scene(kelasPraktikView);
            primaryStage.setScene(kelasPraktikScene);
            primaryStage.setTitle("KelasPraktik");
            primaryStage.show();
        }
   }
    public static void showPlayVideoView(KelasBelajar kelasBelajar, KelasVideo kelasVideo) throws Exception {
    	if (Session.getUser() == null) {
            showLoginView();
        } else {
        	FXMLLoader loader = new FXMLLoader(App.class.getResource("/Views/PlayVideo.fxml"));
            Parent root = loader.load();
            
            PlayVideoController controller = loader.getController();
            controller.initData(kelasBelajar, kelasVideo);
            

            primaryStage.setTitle("KelasBelajar");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
    }
    public static void showPlayQuizView(KelasBelajar kelasBelajar, KelasQuiz kelasQuiz) throws Exception {
    	if (Session.getUser() == null) {
            showLoginView();
        } else {
        	FXMLLoader loader = new FXMLLoader(App.class.getResource("/Views/PlayQuiz.fxml"));
            Parent root = loader.load();
            
            PlayQuizController controller = loader.getController();
            controller.initData(kelasBelajar, kelasQuiz);
            

            primaryStage.setTitle("KelasBelajar");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
