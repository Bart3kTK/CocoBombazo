import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MySquare extends Rectangle {
    private static int classCounter = 0;
    private int index;
    private int row;
    private int column;

    public MySquare(){
        index = classCounter;
        column = index / Settings.getColumns();
        row = index % Settings.getRows();
        classCounter ++;

        setWidth(calcSide());
        setHeight(calcSide());
        setFill(Color.web("#A5CCC5"));
        setStroke(Color.web("#000000"));

        setX(Settings.getMargin() + row * calcSide());
        setY(Settings.getMargin() + column * calcSide());

        setOnMouseClicked(e -> {
            System.out.println("Moj index to " + index);
            setVisible(false);
        });





    }
    
    public double calcSide(){
        return (Settings.getWindowWdth() - Settings.getMargin()*2 ) / (Settings.getRows()*1.0);
    } 
    
}
