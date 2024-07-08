package Controller;

import java.io.File;

import Main.App;
import Models.KelasBelajar;
import Models.KelasVideo;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PlayVideoController {
    
    @FXML
    private MediaView videoView;
    
    private KelasBelajar kelasBelajar;
    private KelasVideo kelasVideo;
    private MediaPlayer mediaPlayer;
    
    public void initData(KelasBelajar kelasBelajar, KelasVideo kelasVideo) {
        this.kelasBelajar = kelasBelajar;
        this.kelasVideo = kelasVideo;
        
        String videoFile = getVideoFilePath(kelasVideo.getLinkVideo());
        Media media = new Media(new File(videoFile).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        
        videoView.setMediaPlayer(mediaPlayer);
        
        videoView.setPreserveRatio(false);
        videoView.setFitWidth(700);
        videoView.setFitHeight(500);
        
        mediaPlayer.play();
    }
    
    private String getVideoFilePath(String relativePath) {
        String projectPath = System.getProperty("user.dir");
        String absolutePath = projectPath + "/src/".replace("/", File.separator) + relativePath.replace("/", File.separator);
        return absolutePath;
    }
    
    @FXML
    private void handleBackButtonClick(MouseEvent event) throws Exception {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
        App.showKelasBelajarView(kelasBelajar);
    }
}
