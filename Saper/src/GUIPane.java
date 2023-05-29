import java.util.ArrayList;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GUIPane {
    private boolean firsClick = true;

    public GUIPane(Pane pane){
        ArrayList<MySquare> objList = new ArrayList<>();
        ArrayList<Integer> usedFields = new ArrayList<>(); //1 - in game 0 - out of game

        for (int i = 0; i<Settings.getRows() * Settings.getColumns(); i++)
            usedFields.add(1);

        for (int i = 0; i < Settings.getRows() * Settings.getColumns(); i++)
        {
          MySquare sq = new MySquare();
          pane.getChildren().add(sq);
          objList.add(sq);
        }

        
        for(int i = 0; i < Settings.getRows() * Settings.getColumns(); i++)
        {
            ArrayList<MySquare> temp = new ArrayList<>();
            int tempRow = (i/Settings.getColumns());
            int tempCol = (i%Settings.getColumns());
            for (int a = -1; a<2; a++){
                for (int b = -1; b<2; b++){
                    if(tempCol+a >= 0 && tempRow+b >= 0 && tempRow+b < Settings.getRows() && tempCol+a < Settings.getColumns() ){
                        temp.add(objList.get((tempRow+b) * Settings.getColumns() + tempCol+a));
                    }
                }
            }
            objList.get(i).setNeig(temp);
        }

        pane.setOnMouseClicked(event -> {
            for (MySquare r : objList){
                if (r.isNowClicked() == true){
                    FieldsGenerator.setActualField(r.getIndex());
                    r.unClick();
                }
            }
            if (event.getButton() == MouseButton.PRIMARY && FieldsGenerator.getActualField() != -1){
                if (firsClick == true){
                    firsClick = false;
                    ArrayList<Integer> startFields = FieldsGenerator.generateStartFields(FieldsGenerator.getActualField());
                    FieldsGenerator.genBombAndNumbers(startFields);
                    for (MySquare r : objList){

                        if(startFields.contains(r.getIndex())){
                            r.setIsClicked();
                        }
                        r.loadStatus(FieldsGenerator.getGeneratedFields().get(r.getIndex()));
                    }

                    
                }
            }
            for (MySquare r : objList){
                r.loadSquare();
                }
                
        });
    }
    
}
