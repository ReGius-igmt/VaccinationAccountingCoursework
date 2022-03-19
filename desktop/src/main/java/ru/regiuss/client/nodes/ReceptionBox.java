package ru.regiuss.client.nodes;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.regiuss.client.controller.ReceptionPreviewController;
import ru.regiuss.client.core.ViewHandler;
import ru.regiuss.client.model.Reception;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReceptionBox extends VBox {

    @FXML
    private ListView<Reception> itemsList;

    @FXML
    private Text listBoxHeader;

    private ListProperty<Reception> receptionsProperty;

    public ReceptionBox(List<Reception> receptions, String header){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/receptionBox.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listBoxHeader.setText(header);
        itemsList.setCellFactory(receptionListView -> new ReceptionCell());
        receptionsProperty = new SimpleListProperty<>(FXCollections.observableList(receptions));
        itemsList.itemsProperty().bind(receptionsProperty);
        itemsList.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2){
                Reception selectedItem = itemsList.getSelectionModel().getSelectedItem();
                if(selectedItem == null)return;
                Stage stage = new Stage();
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/view/receptionPreview.fxml"), ViewHandler.getInstance().getBundle());
               // loader1.setLocation();
                try {
                    Parent p = loader1.load();
                    ReceptionPreviewController controller = loader1.getController();
                    controller.init(selectedItem, stage, this);
                    stage.setScene(new Scene(p));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stage.setResizable(false);
                stage.setTitle(ViewHandler.getInstance().getBundle().getString("reception.header"));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(ViewHandler.getInstance().getStage().getOwner());
                stage.show();
            }
        });
    }

    public void remove(Reception reception){
        this.receptionsProperty.remove(reception);
        if(receptionsProperty.isEmpty()){
            setVisible(false);
            setManaged(false);
        }
    }

}
