import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Controller {

    @FXML
    private Pane MyPane;

    @FXML
    void buttonTestClicked(MouseEvent event) {
        System.out.println(8);
        playMusic();

    }

    public void playMusic(){
        String soundFile = "soundtrack/SoundTest.mp3";
        String path = getClass().getResource(soundFile).getPath();
        Media sound = new Media(new File(path).toURI().toString());
        MediaPlayer snd = new MediaPlayer(sound);
        snd.setCycleCount(MediaPlayer.INDEFINITE);
        snd.play();
        
            
    }


}
