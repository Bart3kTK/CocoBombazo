import java.util.ArrayList;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GUIPane {
    private boolean firsClick = true;
    private int countActive = 0;

    public GUIPane(Pane pane, Text timer){
        ArrayList<MySquare> objList = new ArrayList<>();
        ArrayList<Integer> usedFields = new ArrayList<>();
        TimerThtrad th = new TimerThtrad(timer);
        th.start();

        for (int i = 0; i < Settings.getRows() * Settings.getColumns(); i++)
        {
          usedFields.add(0);
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
            if (event.getButton() == MouseButton.PRIMARY)
            {
                if (FieldsGenerator.getActualField() == -1){
                    for (MySquare r : objList){
                        if (r.isNowClicked() == true){
                            FieldsGenerator.setActualField(r.getIndex());
                            r.unClick();
                        } 
                    }
                }
                if (FieldsGenerator.getActualField() != -1 && firsClick == true){
                        firsClick = false;
                        ArrayList<Integer> startFields = FieldsGenerator.generateStartFields(FieldsGenerator.getActualField());
                        FieldsGenerator.genBombAndNumbers(startFields);

                        for (MySquare r : objList){
                            if(startFields.contains(r.getIndex())) r.setIsClicked();
                            r.loadStatus(FieldsGenerator.getGeneratedFields().get(r.getIndex()));
                            r.loadSquare();
                        }
                    }
                countActive = 0;
                for (MySquare r : objList){
                    if (r.isLoaded() == false)
                            r.loadSquare();
                        if (r.isClicked() == false) countActive ++;
                        if (r.isClicked() == true && r.getStatus() == -1){
                            countActive = Settings.getBombs();
                            break;
                        }
                    }
                }
                if (countActive == Settings.getBombs()){
                    for (MySquare r : objList){
                        if (r.getStatus() == -1) r.setBomb();
                    }
                }
        });
        
    }
    
}
