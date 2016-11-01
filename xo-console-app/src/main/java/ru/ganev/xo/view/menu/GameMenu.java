package ru.ganev.xo.view.menu;

import java.util.Arrays;

public enum GameMenu {

    PLAY,
    SETTINGS,
    EXIT;

    public static void print() {
        Arrays.stream(values()).forEach(System.out::println);
    }
}
