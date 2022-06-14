package ru.regiuss.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ru.regiuss.client.App;
import ru.regiuss.client.core.MainViewHandler;
import ru.regiuss.client.model.User;
import ru.regiuss.client.nodes.MenuItem;
import ru.regiuss.client.page.*;
import ru.regiuss.server.vactination.core.Permissions;

import java.util.List;
import java.util.Locale;

public class MainController {
    @FXML
    private StackPane menu;
    @FXML
    private AnchorPane sidePanel;

    @FXML
    private VBox sideMenu;

    @FXML
    private ToggleGroup sideBarGroup;
    @FXML
    private BorderPane root;
    private MainViewHandler vh;

    public void init(MainViewHandler vh){
        this.vh = vh;
        List<Page> pages = List.of(
                new ProfilePage(),
                new ReceptionsPage(),
                new HistoryPage(),
                new StatisticPage(),
                new UsersPage(),
                new ServicesPage(),
                new PreparatesPage(),
                new LaboratoriesPage(),
                new SettingsPage()
        );
        sideBarGroup = new ToggleGroup();
        root.centerProperty().bind(vh.rootPProperty());
        User user = App.getCurrentUser();
        for(Page p : pages){
            if(user.hasPermission(p.getPermission())){
                ToggleButton button = p.init();
                button.setToggleGroup(sideBarGroup);
                sideBarGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
                    if(button.equals(t1))p.select(vh);
                });
                sideMenu.getChildren().add(button);
            }
        }
        sideBarGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            if(t1 == null)vh.openPage(null);
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
    void onExitClick(ActionEvent event) {
        App.logout();
    }
}
