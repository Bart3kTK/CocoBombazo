import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MySquare extends Rectangle {
    private static int classCounter = 0;
    private int index;
    private int row;
    private int column;
    private boolean isClicked = false;
    private int myStatus = 0;
    private boolean nowClicked = false;
    private int rClickType = 0;

    public MySquare(){
        index = classCounter;
        column = index % Settings.getColumns();
        row = index / Settings.getRows();
        classCounter ++;

        setWidth(calcSide());
        setHeight(calcSide());
        setFill(Color.web("#A5CCC5"));
        setStroke(Color.web("#000000"));

        setY(Settings.getMargin() + row * calcSide());
        setX(Settings.getMargin() + column * calcSide());

        setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY){
                nowClicked = true;
                isClicked = true;
            }

            if (e.getButton() == MouseButton.SECONDARY && isClicked == false){
                if (rClickType == 1)
                {
                    setFill(new ImagePattern(new Image("pics/palma2.png")));
                    rClickType = 2;
                } 
                else if (rClickType == 2)
                {
                    setFill(Color.web("#A5CCC5"));
                    rClickType = 0;
                }
                else
                {
                    setFill(new ImagePattern(new Image("pics/palma.png")));
                    rClickType = 1;
                }
            }
        });
    }
    public int getIndex(){
        return index;
    }
    public void loadStatus(int statId){
        myStatus = statId;
    }

    public int getStatus(){
        return myStatus;
    }

    public void setBomb(){
        setFill(new ImagePattern(new Image("pics/Kokos.png")));
    }
    
    public void setImageNb(int nb){
        if (nb > 0 && nb <= 8)
            setFill(new ImagePattern(new Image("pics/" + Integer.toString(nb) + ".png")));
        else if(nb == 0 || nb == 10) setVisible(false);
        else if (nb == -1) setBomb();
        else System.out.println("Wrong nb in loading ->" + nb);
    }

    public boolean isClicked(){
        return isClicked;
    }

    public void setIsClicked(){
        isClicked = true;
        setImageNb(myStatus);
    }

    public double calcSide(){
        return (Settings.getWindowWdth() - Settings.getMargin()*2 ) / (Settings.getRows()*1.0);
    } 

    public boolean isNowClicked(){
        return nowClicked;
    }

    public void unClick(){
        nowClicked = false;
    }

    
}
