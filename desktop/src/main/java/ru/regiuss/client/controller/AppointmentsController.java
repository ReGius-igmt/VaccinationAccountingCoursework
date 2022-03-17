package ru.regiuss.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import ru.regiuss.client.API;
import ru.regiuss.client.core.ViewHandler;
import ru.regiuss.client.model.Reception;
import ru.regiuss.client.nodes.ReceptionBox;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentsController {

    @FXML
    private ToggleGroup appointmentsTG;

    @FXML
    private VBox boxes;

    @FXML
    private TextField searchBox;

    private ViewHandler vh;

    public void init(ViewHandler vh) {
        this.vh = vh;
        appointmentsTG.selectedToggleProperty().addListener((observableValue, toggle, t1) -> update());
        appointmentsTG.selectToggle(appointmentsTG.getToggles().get(0));
        update();
    }

    private void update(){
        boxes.getChildren().clear();
        int status;
        if(appointmentsTG.getToggles().get(0).isSelected()){
            status = 1;
        }else{
            status = 2;
        }
        List<Reception> receptions = API.getReceptions(status);
        if(receptions == null)return;
        List<Reception> receptionsBox = null;
        Date last = null;
        SimpleDateFormat dt = new SimpleDateFormat("yyyy.MM.dd", vh.getLocale());
        for (Reception reception : receptions) {
            if(last == null || !dt.format(last).equalsIgnoreCase(dt.format(reception.getDate()))){
                if(receptionsBox != null){
                    boxes.getChildren().add(new ReceptionBox(
                            receptionsBox,
                            new SimpleDateFormat("EEEE d MMMM", vh.getLocale()).format(last)
                    ));
                }
                receptionsBox = new ArrayList<>();
                last = reception.getDate();
            }
            receptionsBox.add(reception);
        }
        if(receptionsBox != null){
            boxes.getChildren().add(new ReceptionBox(
                    receptionsBox,
                    new SimpleDateFormat("EEEE d MMMM").format(last)
            ));
        }
    }

    @FXML
    void onSearch(ActionEvent event) {

    }

}
