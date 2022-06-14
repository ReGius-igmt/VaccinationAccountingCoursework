package ru.regiuss.client;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import ru.regiuss.client.api.API;
import ru.regiuss.client.controller.AuthController;
import ru.regiuss.client.core.ModelFactory;
import ru.regiuss.client.core.MainViewHandler;
import ru.regiuss.client.core.ViewModelFactory;
import ru.regiuss.client.exception.AuthException;
import ru.regiuss.client.model.User;
import ru.regiuss.server.vactination.core.Permissions;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.*;
import java.util.prefs.Preferences;

public class App extends Application {

    private static App instance;
    private MainViewHandler vh;
    private Stage stage;
    private API api;
    private User currentUser;
    private Preferences preferences;
    private static ExecutorService executor;
    private Locale locale;
    ResourceBundle bundle;

    public static void logout() {
        setCurrentUser(null);
        instance.vh.openPage(App.class.getResource("/view/auth.fxml"), new AuthController(), true);
        instance.preferences.remove("authData");
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        this.locale = new Locale("ru");
        this.bundle = ResourceBundle.getBundle("lang", this.locale);
        this.stage = stage;
        this.api = new API();
        executor = Executors.newFixedThreadPool(5, r -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);
            return t;
        });
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        vh = new MainViewHandler(vmf);
        vh.init(stage);
        preferences = Preferences.userRoot().node(getClass().getName());
        byte[] authData = preferences.getByteArray("authData", null);
        api.setAuthData(authData);
        Task<User> checkAuthTask = new Task<User>() {
            @Override
            protected User call() throws Exception {
                User user = api.getCurrent();
                if(!user.hasPermission(Permissions.IS_STAFF))throw new AuthException();
                return user;
            }
        };
        checkAuthTask.setOnSucceeded(e->{
            auth(checkAuthTask.getValue());
        });
        checkAuthTask.setOnFailed(e->vh.openPage(getClass().getResource("/view/auth.fxml"), new AuthController(), true));
        executeTask(checkAuthTask);
    }

    public static void auth(User user){
        setCurrentUser(user);
        instance.vh.load(getStage());
    }

    public static void executeTask(Runnable r){
        executor.execute(r);
    }

    public static App getInstance() {
        return instance;
    }

    public static MainViewHandler getViewHandler() {
        return instance.vh;
    }

    public static Stage getStage() {
        return instance.stage;
    }

    public static API getApi() {
        return instance.api;
    }

    public static User getCurrentUser() {
        return instance.currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        instance.currentUser = currentUser;
    }

    public static Preferences getPreferences() {
        return instance.preferences;
    }

    public static Locale getLocale() {
        return instance.locale;
    }

    public static void setLocale(Locale locale) {
        instance.locale = locale;
    }

    public static ResourceBundle getBundle() {
        return instance.bundle;
    }
}
