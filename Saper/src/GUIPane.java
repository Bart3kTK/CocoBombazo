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

        for (int i = 0; i < Settings.getRows() * Settings.getColumns(); i++){
          MySquare sq = new MySquare();
          pane.getChildren().add(sq);
          objList.add(sq);
        }
        for(int i = 0; i < Settings.getRows() * Settings.getColumns(); i++){
            ArrayList<MySquare> temp = new ArrayList<>();
            int tempRow = (i/Settings.getColumns()) - 1;
            int tempCol = (i%Settings.getColumns()) - 1;
            for (int a = 1; a < 10; a++){
                if (tempCol >= 0 && tempRow >= 0){
                    temp.add(objList.get(tempRow * Settings.getColumns() + tempCol));
                }
                tempCol ++;
                if (a%3 == 0){
                    tempCol -= 3;
                    tempRow ++;
                }
            }
            objList.get(i).setNeig(temp);
        }

        pane.setOnMouseClicked(event -> {
            for (MySquare r : objList){
                if (r.isClicked() == true){
                    FieldsGenerator.setActualField(r.getIndex());
                    break;
                }
            }
            if (event.getButton() == MouseButton.PRIMARY){
                if (firsClick == true){
                    firsClick = false;
                    ArrayList<Integer> startFields = FieldsGenerator.generateStartFields(FieldsGenerator.getActualField());
                    FieldsGenerator.genBombAndNumbers(startFields);
                    for (MySquare r : objList){

                        if(startFields.contains(r.getIndex())){
                            r.setVisible(false);
                            usedFields.set(r.getIndex(), 0);
                        }
                        if ( FieldsGenerator.getGeneratedFields().get(r.getIndex()) == -1) r.setBomb();
                    }
                }
                System.out.println(8);
                for (MySquare r : objList){
                    //System.out.println(FieldsGenerator.getGeneratedFields().get(r.getIndex()));

                    if ( FieldsGenerator.getGeneratedFields().get(r.getIndex()) == 0){
                        System.out.println(0);
                        for (int neighbor : FieldsGenerator.getNeighbors(r.getIndex())){
                            if (usedFields.get(neighbor) == 0) {
                                r.setVisible(false); 
                                usedFields.set(r.getIndex(), 0);
                            }
                        }
                    }
                    if (FieldsGenerator.getGeneratedFields().get(r.getIndex()) > 0){
                        for (int neighbor : FieldsGenerator.getNeighbors(r.getIndex())){
                            if (usedFields.get(neighbor) == 0) {
                                usedFields.set(r.getIndex(), -1);
                                r.setImageNb(FieldsGenerator.getGeneratedFields().get(r.getIndex()));

                            }
                        }  
                    }

                    
                    if (r.isClicked()){
                        System.out.println("Moj index to " + r.getIndex() +" a tyle bomb jest obok: " + FieldsGenerator.getGeneratedFields().get(r.getIndex()));
                        r.setIsClicked();
                    }
            }
        }
                
        });

        // for (MySquare r : objList){
        //     if (r.isClicked() == true){
        //         FieldsGenerator.setActualField(r.getIndex());
        //         break;
        //     }
        // }
    }
    
}
