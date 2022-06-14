package ru.regiuss.client.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.regiuss.client.App;
import ru.regiuss.client.api.API;
import ru.regiuss.client.model.Medicine;
import ru.regiuss.client.model.Reception;
import ru.regiuss.client.model.ReceptionStatus;
import ru.regiuss.client.nodes.ReceptionBox;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ReceptionPreviewController {

    @FXML
    private TextArea comment;

    @FXML
    private TilePane filesPane;

    @FXML
    private TilePane medicinesBox;

    @FXML
    private Text serviceNameText;

    @FXML
    private ChoiceBox<ReceptionStatus> statusBox;

    @FXML
    private Text userNameText;

    private Reception reception;
    private Stage stage;
    private ReceptionBox box;
    private Set<Medicine> selectedMedicines;

    public void init(Reception reception, Stage stage, ReceptionBox box){
        this.reception = reception;
        this.box = box;
        statusBox.setItems(FXCollections.observableList(Arrays.stream(ReceptionStatus.values()).toList()));
        statusBox.getSelectionModel().select(ReceptionStatus.values()[reception.getStatus()]);
        userNameText.setText(reception.getUser().getFullName());
        serviceNameText.setText(reception.getService().getName());
        this.stage = stage;
        if(reception.getComment() != null)comment.setText(reception.getComment());
        List<Medicine> medicines = App.getApi().getMedicines(reception.getService().getId());
        selectedMedicines = reception.getMedicines();
        for (Medicine m : medicines){
            CheckBox mBox = new CheckBox(m.getName());
            mBox.setSelected(selectedMedicines.contains(m));
            mBox.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                if (t1)selectedMedicines.add(m);
                else selectedMedicines.remove(m);
                System.out.println(selectedMedicines);
            });
            medicinesBox.getChildren().add(mBox);
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        reception.setMedicines(selectedMedicines);
        reception.setStatus(statusBox.getValue().getStatus());
        reception.setComment(comment.getText());
        App.getApi().saveReception(reception);
        //if(reception.getDate().after(new Date(System.currentTimeMillis())))box.remove(reception);
        stage.close();
    }

}
