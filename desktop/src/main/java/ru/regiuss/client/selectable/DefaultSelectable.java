package ru.regiuss.client.selectable;

import ru.regiuss.client.controller.Controllable;
import ru.regiuss.client.controller.Controller;
import ru.regiuss.client.core.ViewHandler;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class DefaultSelectable implements Selectable{

    private final URL view;
    private final Controllable controllable;

    public DefaultSelectable(URL view, Controllable controllable){
        this.view = view;
        this.controllable = controllable;
    }

    @Override
    public void select(ViewHandler vh) {
        try {
            vh.openPage(view, controllable.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
