package ru.regiuss.client.core;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import ru.regiuss.client.App;
import ru.regiuss.client.controller.*;
import ru.regiuss.client.nodes.LoadNode;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainViewHandler implements ViewHandler{
    private static MainViewHandler instance;

    private final ViewModelFactory vmf;
    private ObjectProperty<Node> rootP;

    public MainViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
        instance = this;
    }

    public static MainViewHandler getInstance() {
        return instance;
    }

    public void init(Stage stage){
        stage.setTitle("Стопкоронавирус");
        stage.setMinWidth(900);
        stage.setMinHeight(700);
        stage.setScene(new Scene(new LoadNode()));
        stage.show();
    }

    public void load(Stage stage){
        this.rootP = new SimpleObjectProperty<>();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"), App.getBundle());
        try {
            Parent p = loader.load();
            MainController rootController = loader.getController();
            rootController.init(this);
            if(stage.getScene() == null)stage.setScene(new Scene(p));
            else stage.getScene().setRoot(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //openPage(getClass().getResource("/view/profile.fxml"), new ProfileController());
    }

    @Override
    public void openPage(URL url, Controller controller){
        openPage(url, controller, false);
    }

    @Override
    public void openPage(URL url, Controller controller, boolean root){
        ViewHandler vh = this;
        Task<Parent> loadPage = new Task<Parent>() {
            @Override
            protected Parent call() throws Exception {
                FXMLLoader loader = new FXMLLoader(url, App.getBundle());
                loader.setController(controller);
                Parent p = loader.load();
                controller.init(vh);
                return p;
            }
        };
        loadPage.setOnSucceeded(e->{
            if(root) App.getStage().getScene().setRoot(loadPage.getValue());
            else {
                ScrollPane sp = new ScrollPane(loadPage.getValue());
                sp.setPadding(new Insets(20));
                sp.setFitToWidth(true);
                sp.setFitToHeight(true);
                rootP.setValue(sp);
            }
        });
        loadPage.setOnFailed(e->e.getSource().getException().printStackTrace());
        App.executeTask(loadPage);
    }

    public void openAppointments(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/appointments.fxml"), App.getBundle());
        Parent p = null;
        try {
            p = loader.load();
            ReceptionsController controller = loader.getController();
            controller.init(this);
            rootP.set(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node getRootP() {
        return rootP.get();
    }

    public ObjectProperty<Node> rootPProperty() {
        return rootP;
    }

    @Override
    public void openPage(Node node) {
        rootP.setValue(node);
    }
}
