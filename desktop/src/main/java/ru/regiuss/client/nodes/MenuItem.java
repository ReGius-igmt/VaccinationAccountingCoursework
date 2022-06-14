package ru.regiuss.client.nodes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MenuItem extends ToggleButton{
    @FXML
    private ImageView img;

    public MenuItem(String text, String icon){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/menuItem.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        setText(text);
        img.setImage(new Image(getClass().getResourceAsStream(icon)));
    }
}
