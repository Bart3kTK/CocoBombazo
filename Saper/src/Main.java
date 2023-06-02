import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
      }
      public void launchApp(String[] args){
        Application.launch(args);
      }
      @Override
      public void start(Stage primaryStage) throws IOException {
        new GUI(primaryStage);
      }
    
}
