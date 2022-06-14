package ru.regiuss.client.page;

import javafx.scene.control.ToggleButton;
import ru.regiuss.client.App;
import ru.regiuss.client.controller.ProfileController;
import ru.regiuss.client.nodes.MenuItem;
import ru.regiuss.client.selectable.DefaultSelectable;
import ru.regiuss.server.vactination.core.Permissions;

public class HistoryPage extends SimplePage{
    public HistoryPage() {
        super(new DefaultSelectable(HistoryPage.class.getResource("/view/profile.fxml"), ProfileController::new));
    }

    @Override
    public ToggleButton init() {
        return new MenuItem(App.getBundle().getString("sideBar.history"), "/img/history.png");
    }

    @Override
    public String getPermission() {
        return Permissions.IS_STAFF;
    }
}
