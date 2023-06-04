
import java.io.IOException;

import javafx.scene.Parent;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI{


    public GUI(Stage stage) throws IOException {
      Parent parent = FXMLLoader.load(getClass().getResource("Saper.fxml"));

      Scene scene = new Scene(parent);


      Pane pane = (Pane) scene.lookup("#MyPane");
      Text timer = (Text) scene.lookup("#MyTimer");
      Text score = (Text) scene.lookup("#MyScore");
      Text bestScore = (Text) scene.lookup("#MyBestScore");

      pane.setClip(new Rectangle(0,0, Settings.getWindowWdth(), Settings.getWindowHeight()));

      stage.setHeight(Settings.getWindowHeight());
      stage.setWidth(Settings.getWindowWdth());
      stage.setOnCloseRequest(e ->{
        Platform.exit();
        System.exit(0);
      });
      
      new GUIPane(pane, timer, score, bestScore);

      stage.setTitle("Coco Bombazo");
      stage.setScene(scene);
      stage.show();

    
    }
}
