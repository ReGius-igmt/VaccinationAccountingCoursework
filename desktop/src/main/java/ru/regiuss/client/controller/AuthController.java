package ru.regiuss.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.regiuss.client.api.API;
import ru.regiuss.client.App;
import ru.regiuss.client.core.MainViewHandler;
import ru.regiuss.client.core.ViewHandler;
import ru.regiuss.client.exception.AuthException;
import ru.regiuss.client.model.User;
import ru.regiuss.server.vactination.core.Permissions;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthController implements Controller {

    MainViewHandler vh;

    @FXML
    private TextField loginField;

    @FXML
    private CheckBox staySystemCB;

    @FXML
    private PasswordField passField;

    @FXML
    void onLogin(ActionEvent event) {
        if(this.vh == null)return;
        API api = App.getApi();
        String login = loginField.getText();
        String pass = passField.getText();
        if(login.strip().isEmpty() || pass.strip().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Заполните все поля").show();
            return;
        }
        byte[] authData = Base64.getEncoder().encode((login + ":" + pass).getBytes(StandardCharsets.UTF_8));
        api.setAuthData(authData);
        try {
            User user = api.getCurrent();
            if(!user.hasPermission(Permissions.IS_STAFF))throw new AuthException();
            if(staySystemCB.isSelected())App.getPreferences().putByteArray("authData", authData);
            App.auth(user);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Ошибка").show();
        }
    }

    @Override
    public void init(ViewHandler vh) {
        if(!(vh instanceof MainViewHandler))return;
        this.vh = (MainViewHandler) vh;
    }
}
