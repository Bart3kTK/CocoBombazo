package com.example;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI{

    public GUI(Stage stage) throws IOException {

      Parent parent = FXMLLoader.load(getClass().getResource("gameChooser.fxml"));
      Scene scene = new Scene(parent);
      Button jeden = (Button) scene.lookup("#jeden");
      Button dwa = (Button) scene.lookup("#dwa");
      Button trzy = (Button) scene.lookup("#trzy");
      Button cztery = (Button) scene.lookup("#cztery");

      jeden.setOnAction(e -> {
        Settings.setRows(8);
        Settings.setColumns(8);
        Settings.setBombs(10);
        Settings.setStartFields(10);
        try {
          loadGame(stage);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      });

      dwa.setOnAction(e -> {
        Settings.setRows(16);
        Settings.setColumns(16);
        Settings.setBombs(50);
        Settings.setStartFields(50);
        try {
          loadGame(stage);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      });

      trzy.setOnAction(e -> {
        Settings.setRows(25);
        Settings.setColumns(25);
        Settings.setBombs(150);
        Settings.setStartFields(150);
        try {
          loadGame(stage);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      });

      cztery.setOnAction(e -> {
        Settings.setRows(50);
        Settings.setColumns(50);
        Settings.setBombs(250);
        Settings.setStartFields(300);
        try {
          loadGame(stage);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      });
      stage.setTitle("Coco Bombazo");
      stage.setScene(scene);
      stage.show();

    
    }
    public void loadGame(Stage stage) throws IOException
    {
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
