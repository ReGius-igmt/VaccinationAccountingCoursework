package ru.regiuss.client.nodes;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.regiuss.client.App;
import ru.regiuss.client.controller.ReceptionPreviewController;
import ru.regiuss.client.core.MainViewHandler;
import ru.regiuss.client.model.Reception;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReceptionBox extends VBox {

    @FXML
    private TableView<Reception> itemsTable;

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

        TableColumn<Reception, String> column = new TableColumn<>();
        column.setCellValueFactory(cData -> new SimpleStringProperty("#" + cData.getValue().getId()));
        itemsTable.getColumns().add(column);

        column = new TableColumn<>();
        column.setCellValueFactory(cData -> new SimpleStringProperty(cData.getValue().getUser().getFullName()));
        itemsTable.getColumns().add(column);

        column = new TableColumn<>();
        column.setCellValueFactory(cData -> new SimpleStringProperty(cData.getValue().getService().getName()));
        itemsTable.getColumns().add(column);

        column = new TableColumn<>();
        column.setCellValueFactory(cData -> new SimpleStringProperty(new SimpleDateFormat("HH:mm").format(cData.getValue().getDate())));
        itemsTable.getColumns().add(column);




        receptionsProperty = new SimpleListProperty<>(FXCollections.observableList(receptions));
        itemsTable.itemsProperty().bind(receptionsProperty);
        itemsTable.setFixedCellSize(25);
        itemsTable.prefHeightProperty().bind(itemsTable.fixedCellSizeProperty().multiply(receptionsProperty.sizeProperty().add(1.02)));
        itemsTable.minHeightProperty().bind(itemsTable.prefHeightProperty());
        itemsTable.maxHeightProperty().bind(itemsTable.prefHeightProperty());
        itemsTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2){
                Reception selectedItem = itemsTable.getSelectionModel().getSelectedItem();
                if(selectedItem == null)return;
                Stage stage = new Stage();
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/view/receptionPreview.fxml"), App.getBundle());
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
                stage.setTitle(App.getBundle().getString("reception.header"));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(App.getStage().getOwner());
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

    public TableView<Reception> getItemsTable() {
        return itemsTable;
    }
}
