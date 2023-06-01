import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GUIPane {
    private boolean firsClick = true;
    private int[][] gameMap;
    private int fieldsCounter = 0;

    public GUIPane(Pane pane, Text timer){
        TimerThtrad th = new TimerThtrad(timer);
        MySquare[][] squareList = new MySquare[Settings.getColumns()][Settings.getRows()];
        int[][] showedFields = new int [Settings.getColumns()][Settings.getRows()];

        for(int b = 0; b < Settings.getRows(); b++){
            for(int a = 0; a < Settings.getColumns(); a++){
                    MySquare s = new MySquare();
                    squareList[a][b] = s;
                    pane.getChildren().add(s);
                    
                }
        }

        pane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
            {
                if (true){

                    for(int a = 0; a < Settings.getColumns(); a++){
                        for(int b = 0; b < Settings.getRows(); b++){
                            if (squareList[a][b].isNowClicked() == true){
                                FieldsGenerator.setActualField(squareList[a][b].getIndex());
                                squareList[a][b].unClick();
                                showedFields[a][b] = 1;
                                System.out.println(showedFields[a][b]);
                                break;
                            }
                            
                        }
                    }
                }
                if (FieldsGenerator.getActualField() != -1 && firsClick == true)
                {
                    firsClick = false;
                    th.start();
                    
                    FieldsGenerator.generateStartFields();
                    FieldsGenerator.genBombAndNumbers();
                    gameMap = FieldsGenerator.getGeneratedFields();

                    for(int a = 0; a < Settings.getColumns(); a++)
                    {
                        for(int b = 0; b < Settings.getRows(); b++)
                        {
                            squareList[a][b].loadStatus(gameMap[a][b]);
    
                            if (gameMap[a][b] == 10)
                                showedFields[a][b] = 1;
                            
                        }
                    }
                }

                int changes = 1;
                while (changes != 0)
                {
                    changes = 0;
                    for(int a = 0; a < Settings.getColumns(); a++)
                    {
                        for(int b = 0; b < Settings.getRows(); b++)
                        {
                            if (showedFields[a][b] == 1){
                                squareList[a][b].setIsClicked();
                                changes ++;
                                showedFields[a][b] = 2;
                                if (gameMap[a][b] == 0 || gameMap[a][b] == 10){

                                    for (int index : FieldsGenerator.getNeighbors(b*Settings.getColumns() + a))
                                    {
                                        int row = index / Settings.getColumns();
                                        int column = index % Settings.getColumns();
                                        if (showedFields[column][row] == 0)
                                        {
                                            showedFields[column][row] = 1;
                                        }
                                    }
                                }


                                
                            } 
                        }
                    }
                }
                fieldsCounter = 0;
                for(int b = 0; b < Settings.getRows(); b++)
                {
                    for(int a = 0; a < Settings.getColumns(); a++){
                        if(showedFields[a][b] == 0) fieldsCounter ++;
                    }
                }

                for(int b = 0; b < Settings.getRows(); b++){
                    for(int a = 0; a < Settings.getColumns(); a++)
                    {
                            if (( showedFields[a][b] > 0 && gameMap[a][b] == -1) || fieldsCounter == Settings.getBombs())
                            {
                                for(int row = 0; row < Settings.getRows(); row++){
                                    for(int col = 0; col < Settings.getColumns(); col++){
                                        if (gameMap[col][row] == -1) squareList[col][row].setIsClicked();
                                    }
                                }
                                th.stopRun();
                                break;
                            }
                            
                    }
                }
                System.out.println(fieldsCounter);
            }


        });
        
    }
    
}
