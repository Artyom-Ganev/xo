package ru.ganev.xo.view.menu;

import java.util.Arrays;

public enum GameMenu {

    PLAY(1, "Play"),
    SETTINGS(2, "Settings"),
    EXIT(3, "Exit");

    private final int position;
    private final String caption;

    GameMenu(int position, String caption) {
        this.position = position;
        this.caption = caption;
    }

    public static void print() {
        Arrays.stream(values())
                .map(v -> v.getPosition() + " - " + v.getCaption())
                .forEach(System.out::println);
    }

    public int getPosition() {
        return position;
    }

    public String getCaption() {
        return caption;
    }
}
