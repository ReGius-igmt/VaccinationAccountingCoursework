package ru.regiuss.client.page;

import org.w3c.dom.Node;
import ru.regiuss.client.core.ViewHandler;
import ru.regiuss.client.selectable.Selectable;

public abstract class SimplePage implements Page{

    protected Selectable selectable;

    public SimplePage(Selectable selectable){
        this.selectable = selectable;
    }

    @Override
    public void select(ViewHandler vh) {
        selectable.select(vh);
    }

    public Selectable getSelectable() {
        return selectable;
    }

    public void setSelectable(Selectable selectable) {
        this.selectable = selectable;
    }
}
