package ru.regiuss.client.nodes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ru.regiuss.client.model.Reception;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ReceptionCell extends ListCell<Reception> {

    private Reception reception;

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
