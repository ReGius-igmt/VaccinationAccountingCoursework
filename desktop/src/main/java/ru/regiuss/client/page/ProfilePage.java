package ru.regiuss.client.page;

import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import ru.regiuss.client.App;
import ru.regiuss.client.controller.ProfileController;
import ru.regiuss.client.nodes.MenuItem;
import ru.regiuss.client.selectable.DefaultSelectable;
import ru.regiuss.server.vactination.core.Permissions;

public class ProfilePage extends SimplePage{
    public ProfilePage() {
        super(new DefaultSelectable(ProfilePage.class.getResource("/view/profile.fxml"), ProfileController::new));
    }

    @Override
    public ToggleButton init() {
        return new MenuItem(App.getBundle().getString("sideBar.profile"), "/img/profile.png");
    }

    @Override
    public String getPermission() {
        return Permissions.IS_STAFF;
    }
}
