package ru.regiuss.client.controller;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import ru.regiuss.client.App;
import ru.regiuss.client.api.ReceptionsRequest;
import ru.regiuss.client.core.MainViewHandler;
import ru.regiuss.client.core.ViewHandler;
import ru.regiuss.client.model.Laboratory;
import ru.regiuss.client.model.Reception;
import ru.regiuss.client.nodes.LoadNode;
import ru.regiuss.client.nodes.ReceptionBox;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReceptionsController implements Controller{
    @FXML
    private VBox boxes;

    @FXML
    private ChoiceBox<Laboratory> laboratoryBox;

    @FXML
    private TextField searchBox;

    private ViewHandler vh;
    private List<TableView<Reception>> tables;

    @Override
    public void init(ViewHandler vh) {
        this.vh = vh;
        this.tables = new ArrayList<>();
        laboratoryBox.setConverter(new StringConverter<Laboratory>() {
            @Override
            public String toString(Laboratory laboratory) {
                if(laboratory == null)return null;
                return laboratory.getDisplayName();
            }

            @Override
            public Laboratory fromString(String s) {
                return null;
            }
        });

        laboratoryBox.setOnAction(event -> {
            System.out.println(laboratoryBox.getSelectionModel().getSelectedItem().getId());
        });

        laboratoryBox.setItems(FXCollections.observableArrayList(App.getCurrentUser().getLaboratories()));
        if(laboratoryBox.getItems().isEmpty()){
            laboratoryBox.setVisible(false);
            laboratoryBox.setManaged(false);
        }else laboratoryBox.getSelectionModel().select(0);
        update();
    }

    private void update(){
        boxes.getChildren().clear();
        LoadNode loadNode = new LoadNode();
        boxes.getChildren().add(loadNode);
        Task<List<Reception>> loadReceptionsTask = new Task<>() {
            @Override
            protected List<Reception> call() {
                return App.getApi().execute(
                        ReceptionsRequest.builder().status(0).build()
                );
            }
        };
        loadReceptionsTask.setOnSucceeded(e->{
            List<Reception> receptions = loadReceptionsTask.getValue();
            if(receptions == null)return;
            SimpleDateFormat dt = new SimpleDateFormat("EEEE d MMMM", App.getLocale());
            receptions.stream()
                    .collect(Collectors.groupingBy(reception -> dt.format(reception.getDate())))
                    .forEach((date, values) -> {
                        ReceptionBox box = new ReceptionBox(
                                values,
                                date
                        );
                        addTable(box.getItemsTable());
                        boxes.getChildren().add(box);
                    });
            boxes.getChildren().remove(loadNode);
        });
        App.executeTask(loadReceptionsTask);
    }

    @FXML
    void onSearch(ActionEvent event) {

    }

    public void addTable(TableView<Reception> table){
        tables.add(table);
    }

}
