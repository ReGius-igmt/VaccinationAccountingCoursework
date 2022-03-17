package ru.regiuss.client.controller;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ru.regiuss.client.core.ViewHandler;

import java.util.Locale;

public class MainController {
    @FXML
    private StackPane menu;
    @FXML
    private AnchorPane sidePanel;
    @FXML
    private ToggleGroup sideBarGroup;
    @FXML
    private BorderPane root;
    private ViewHandler vh;

    public void init(ViewHandler vh){
        this.vh = vh;
        root.centerProperty().bind(vh.rootPProperty());
        sideBarGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            switch (t1.getUserData().toString()){
                case "appointments" -> vh.openAppointments();
            }
        });
        menu.setOnMouseClicked(mouseEvent -> {
            if(sidePanel.getPrefWidth() == sidePanel.getMinWidth()){
                sidePanel.setPrefWidth(sidePanel.getMaxWidth());
                sidePanel.getStyleClass().remove("min");
            }else{
                sidePanel.setPrefWidth(sidePanel.getMinWidth());
                sidePanel.getStyleClass().add("min");
            }
        });
    }

    @FXML
    void onLangChange(ActionEvent event) {
        vh.setLocale(vh.getLocale().equals(Locale.ENGLISH) ? new Locale("ru") : Locale.ENGLISH);
        vh.load();
    }

    public void setSelected(int i){
        sideBarGroup.selectToggle(sideBarGroup.getToggles().get(i));
    }

}
