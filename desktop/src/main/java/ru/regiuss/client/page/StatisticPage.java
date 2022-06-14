package ru.regiuss.client.page;

import javafx.scene.control.ToggleButton;
import ru.regiuss.client.App;
import ru.regiuss.client.controller.ReceptionsController;
import ru.regiuss.client.nodes.MenuItem;
import ru.regiuss.client.selectable.DefaultSelectable;
import ru.regiuss.server.vactination.core.Permissions;

public class StatisticPage extends SimplePage{
    public StatisticPage() {
        super(new DefaultSelectable(ReceptionsPage.class.getResource("/view/appointments.fxml"), ReceptionsController::new));
    }

    @Override
    public ToggleButton init() {
        return new MenuItem(App.getBundle().getString("sideBar.statistics"), "/img/analytics.png");
    }

    @Override
    public String getPermission() {
        return Permissions.IS_STAFF;
    }
}
