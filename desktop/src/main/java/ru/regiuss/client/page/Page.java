package ru.regiuss.client.page;

import javafx.scene.control.ToggleButton;
import ru.regiuss.client.core.ViewHandler;

public interface Page {
    ToggleButton init();
    String getPermission();
    void select(ViewHandler vh);
}
