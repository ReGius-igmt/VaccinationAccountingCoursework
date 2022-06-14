package ru.regiuss.client.core;

import javafx.scene.Node;
import ru.regiuss.client.controller.Controller;

import java.net.URL;

public interface ViewHandler {
    void openPage(URL url, Controller controller);
    void openPage(URL url, Controller controller, boolean root);
    void openPage(Node node);
}
