package com.example;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;

public class GUIDialog extends Dialog<String> {
    
    public GUIDialog(String title, String mes){
        setTitle(title);
        ButtonType type = new ButtonType("Ok", ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().add(type);
        setContentText(mes);
        showAndWait();
    }

}
