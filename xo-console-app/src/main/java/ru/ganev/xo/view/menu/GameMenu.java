package ru.ganev.xo.view.menu;

import java.util.Arrays;

import ru.ganev.xo.exception.IncorrectMenuChoice;

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

    public static GameMenu select(String input) throws IncorrectMenuChoice {
        int choice = Integer.parseInt(input);
        switch (choice) {
            case 1:
                return PLAY;
            case 2:
                return SETTINGS;
            case 3:
                return EXIT;
            default:
                throw new IncorrectMenuChoice(input);
        }
    }

    public int getPosition() {
        return position;
    }

    public String getCaption() {
        return caption;
    }
}
