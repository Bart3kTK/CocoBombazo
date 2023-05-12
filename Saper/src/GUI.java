
import java.io.IOException;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUI{


    public GUI(Stage stage) throws IOException {
      Parent parent = FXMLLoader.load(getClass().getResource("Saper.fxml"));

      Scene scene = new Scene(parent);


      Pane pane = (Pane) scene.lookup("#MyPane");
      pane.setClip(new Rectangle(0,0, stage.getWidth(), stage.getHeight()));
      Rectangle rec = new Rectangle(0,0,2000,2000);
      rec.setFill(Color.AQUA);
      pane.getChildren().add(rec);
      stage.setHeight(850);
      stage.setWidth(700);

      stage.setTitle("Saper");
      stage.setScene(scene);
      stage.show();

    
    }
}
