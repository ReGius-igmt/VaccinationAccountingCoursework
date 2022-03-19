package ru.regiuss.client.nodes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ru.regiuss.client.model.Reception;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceptionCell extends ListCell<Reception> {

    private Reception reception;

    @FXML
    private ImageView img;

    @FXML
    private Rectangle colorBox;

    @FXML
    private Text id;

    @FXML
    private Text name;

    @FXML
    private Text service;

    @FXML
    private Text time;

    private HBox node;

    public ReceptionCell(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/reception.fxml"));
        loader.setController(this);
        try {
            node = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(){
        this.id.setText("#" + reception.getId());
        this.name.setText(reception.getUser().getFullName());
        this.service.setText(reception.getService().getName());
        this.time.setText(new SimpleDateFormat("HH:mm").format(reception.getDate()));

        if(reception.getDate().before(new Date(System.currentTimeMillis()))){
            if(reception.getStatus() == 2){
                img.setImage(new Image(getClass().getResourceAsStream("/img/success.png")));
                colorBox.setFill(Paint.valueOf("#1BC300"));
            }
            else {
                img.setImage(new Image(getClass().getResourceAsStream("/img/error.png")));
                colorBox.setFill(Paint.valueOf("#EE3F58"));
            }
        }else {
            img.setImage(new Image(getClass().getResourceAsStream("/img/clock.png")));
            colorBox.setFill(Paint.valueOf("#FBE200"));
        }
    }

    @Override
    protected void updateItem(Reception reception, boolean b) {
        super.updateItem(reception, b);
        if(b){
            setGraphic(null);
        }else {
            this.reception = reception;
            load();
            setGraphic(node);
        }
    }
}
