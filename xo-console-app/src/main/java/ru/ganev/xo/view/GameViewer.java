package ru.ganev.xo.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ru.ganev.xo.exception.IncorrectMenuChoice;
import ru.ganev.xo.model.GameSettings;
import ru.ganev.xo.view.menu.GameMenu;

import static java.lang.System.out;

public class GameViewer implements View {

    private BufferedReader reader;
    private GameSettings gameSettings;

    public GameViewer() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void printSettingsMenu() {
        out.println("1 - Board size");
        out.println("2 - Players count");
        out.println("3 - Back");
    }

    @Override
    public GameSettings startGameMenu(GameSettings gameSettings) {
        this.gameSettings = gameSettings.clone();
        GameMenu.print();
        GameMenu selected = null;
        do {
            try {
                selected = GameMenu.select(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (IncorrectMenuChoice e) {
                out.println("Incorrect menu choice: " + e.getMessage());
            }
        } while (selected == null);
        executeMenu(selected);
        return this.gameSettings;
    }

    private void executeMenu(GameMenu selected) {
        switch (selected) {
            case PLAY:
                playMenu();
            case SETTINGS:
                settingsMenu();
                GameMenu.print();
            default:
                System.exit(0);
        }
    }

    private void playMenu() {

    }

    private void settingsMenu() {
        printSettingsMenu();
        boolean exit = false;
        while (!exit) {
            switch (readChoice()) {
                case 1:
                    out.println("Please, enter game board size");
                    int size = readChoice();
                    if (size < GameSettings.DEFAULT_DIMENSION || size < 0) {
                        out.println("Incorrect choice");
                    } else {
                        gameSettings.setDimension(size);
                        out.println("Settings saved");
                    }
                    printSettingsMenu();
                    break;
                case 2:
                    out.println("Please, enter players count");
                    int count = readChoice();
                    if (count > GameSettings.DEFAULT_PLAYERS_COUNT || count < 0) {
                        out.println("Incorrect choice");
                    } else {
                        gameSettings.setPlayersCount(count);
                        out.println("Settings saved");
                    }
                    printSettingsMenu();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
            }
        }
    }

    private int readChoice() {
        try {
            String choice = reader.readLine();
            return Integer.parseInt(choice);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            out.println("Incorrect choice");
        }
        return -1;
    }
}