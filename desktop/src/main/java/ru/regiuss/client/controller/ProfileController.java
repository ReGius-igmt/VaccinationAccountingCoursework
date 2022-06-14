package ru.regiuss.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ru.regiuss.client.App;
import ru.regiuss.client.core.ViewHandler;
import ru.regiuss.client.model.User;

public class ProfileController implements Controller{

    private ViewHandler vh;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField patronymicField;

    @FXML
    private TextField phoneField;

    @FXML
    private ChoiceBox<?> xexBox;

    @FXML
    void onChangePasswordClick(ActionEvent event) {

    }

    @FXML
    void onSaveClick(ActionEvent event) {

    }

    @Override
    public void init(ViewHandler vh) {
        this.vh = vh;
        User user = App.getCurrentUser();
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        if(user.getPatronymic() != null)patronymicField.setText(user.getPatronymic());
        if(user.getPhone() != null)phoneField.setText(user.getPhone());
    }
}
