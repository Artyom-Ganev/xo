package ru.ganev.xo.view.menu;

public abstract class MenuLine {

    private String caption;

    public MenuLine(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public abstract void execute();
}
