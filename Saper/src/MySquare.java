import java.util.ArrayList;

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
    private ArrayList<MySquare> neig = new ArrayList<>();
    private int myStatus = -1;
    private boolean nowClicked = false;

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
                setVisible(false);
            }
            if (e.getButton() == MouseButton.SECONDARY){
                setFill(new ImagePattern(new Image("pics/palma.png")));
            }

        });





    }
    public int getIndex(){
        return index;
    }
    public void setNeig(ArrayList<MySquare> n){
        neig = n;
    }
    public void loadStatus(int statId){
        myStatus = statId;
    }

    public int getStatus(){
        return myStatus;
    }
    public void loadSquare(){
        for (MySquare n : neig){
            if(n.getStatus() == 0 && n.isClicked){
                if (myStatus > 0) setImageNb(myStatus);
                else setIsClicked();

            }
        }
        if (isClicked == true && myStatus == 0){
            setVisible(false);
        }
    }

    public void setBomb(){
        setFill(new ImagePattern(new Image("pics/Kokos.png")));
    }

    public void setImageNb(int nb){
        if (nb >= 0 && nb <= 8)
            setFill(new ImagePattern(new Image("pics/" + Integer.toString(nb) + ".png")));
        else System.out.println("Wrong nb in loading ->" + nb);
    }

    public boolean isClicked(){
        return isClicked;
    }

    public void setIsClicked(){
        isClicked = true;
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
