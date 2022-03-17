package ru.regiuss.client.core;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.regiuss.client.controller.AppointmentsController;
import ru.regiuss.client.controller.MainController;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ViewHandler {
    private static ViewHandler instance;

    private final ViewModelFactory vmf;
    private final Stage stage;
    private MainController rootController;
    private ObjectProperty<Node> rootP;
    private Locale locale;
    ResourceBundle bundle;

    public ViewHandler(ViewModelFactory vmf, Stage stage) {
        this.vmf = vmf;
        this.stage = stage;
        this.locale = new Locale("ru");
        instance = this;
    }

    public Stage getStage() {
        return stage;
    }

    public static ViewHandler getInstance() {
        return instance;
    }

    public void init(){
        stage.setTitle("Стопкоронавирус");
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setMaximized(true);
        load();
        stage.show();
    }

    public void load(){
        this.rootP = new SimpleObjectProperty<>();
        this.bundle = ResourceBundle.getBundle("lang", this.locale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"), bundle);
        //loader.setLocation(getClass().getResource("/view/main.fxml"));
        Parent p = null;
        try {
            p = loader.load();
            rootController = loader.getController();
            rootController.init(this);
            if(stage.getScene() == null)stage.setScene(new Scene(p));
            else stage.getScene().setRoot(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootController.setSelected(0);
    }

    public void openAppointments(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/appointments.fxml"), bundle);
        Parent p = null;
        try {
            p = loader.load();
            AppointmentsController controller = loader.getController();
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

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }
}
