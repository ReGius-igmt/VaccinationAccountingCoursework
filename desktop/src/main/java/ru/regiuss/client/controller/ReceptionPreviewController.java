package ru.regiuss.client.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.regiuss.client.API;
import ru.regiuss.client.model.Medicine;
import ru.regiuss.client.model.Reception;
import ru.regiuss.client.nodes.ReceptionBox;

import java.util.Date;
import java.util.List;

public class ReceptionPreviewController {

    @FXML
    private ChoiceBox<Medicine> choiceBox;

    @FXML
    private Text serviceNameTxt;

    @FXML
    private Text userNameTxt;

    private Reception reception;
    private Stage stage;
    private ReceptionBox box;

    public void init(Reception reception, Stage stage, ReceptionBox box){
        this.reception = reception;
        this.box = box;
        userNameTxt.setText(reception.getUser().getFullName());
        serviceNameTxt.setText(reception.getService().getName());
        this.stage = stage;

        List<Medicine> medicines = API.getMedicines(reception.getService().getId());
        choiceBox.setItems(FXCollections.observableList(medicines));
        choiceBox.getSelectionModel().select(0);
    }

    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        reception.setMedicine(choiceBox.getValue());
        reception.setStatus(2);
        API.saveReception(reception);
        box.remove(reception);
        stage.close();
    }

}
