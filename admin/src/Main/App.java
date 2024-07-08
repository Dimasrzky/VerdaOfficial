package Main;

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
        Parent loginView = FXMLLoader.load(App.class.getResource("/Views/Login.fxml"));
        Scene loginScene = new Scene(loginView);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
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
            	Parent pelatihanView = FXMLLoader.load(App.class.getResource("/Views/Pelatihan.fxml"));
                Scene pelatihanScene = new Scene(pelatihanView);
                primaryStage.setScene(pelatihanScene);
                primaryStage.setTitle("Pelatihan");
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
    public static void showTambahLowonganView() throws Exception {
        if (Session.getUser() == null) {
            showLoginView();
        } else {
            Parent lowonganView = FXMLLoader.load(App.class.getResource("/Views/TambahLowongan.fxml"));
            Scene lowonganScene = new Scene(lowonganView);
            primaryStage.setScene(lowonganScene);
            primaryStage.setTitle("Tambah Lowongan");
            primaryStage.show();
        }
    }
    public static void showTambahPelatihanView() throws Exception {
        if (Session.getUser() == null) {
            showLoginView();
        } else {
            Parent lowonganView = FXMLLoader.load(App.class.getResource("/Views/TambahPelatihan.fxml"));
            Scene lowonganScene = new Scene(lowonganView);
            primaryStage.setScene(lowonganScene);
            primaryStage.setTitle("Tambah Pelatihan");
            primaryStage.show();
        }
    }
    public static void showTambahLanggananView() throws Exception {
        if (Session.getUser() == null) {
            showLoginView();
        } else {
            Parent lowonganView = FXMLLoader.load(App.class.getResource("/Views/TambahLangganan.fxml"));
            Scene lowonganScene = new Scene(lowonganView);
            primaryStage.setScene(lowonganScene);
            primaryStage.setTitle("Tambah Langganan");
            primaryStage.show();
        }
    }
    public static void showTambahKomunitasView() throws Exception {
        if (Session.getUser() == null) {
            showLoginView();
        } else {
            Parent lowonganView = FXMLLoader.load(App.class.getResource("/Views/TambahKomunitas.fxml"));
            Scene lowonganScene = new Scene(lowonganView);
            primaryStage.setScene(lowonganScene);
            primaryStage.setTitle("Tambah Langganan");
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}