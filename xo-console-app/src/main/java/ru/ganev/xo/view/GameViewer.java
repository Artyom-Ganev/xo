package ru.ganev.xo.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Predicate;

import ru.ganev.xo.exception.AlreadySelectedFigure;
import ru.ganev.xo.exception.IncorrectMenuChoice;
import ru.ganev.xo.model.Coordinate;
import ru.ganev.xo.model.Figure;
import ru.ganev.xo.model.GameSettings;
import ru.ganev.xo.model.Player;
import ru.ganev.xo.view.menu.GameMenu;

import static java.lang.System.exit;
import static java.lang.System.out;
import static ru.ganev.xo.model.Figure.O;
import static ru.ganev.xo.model.Figure.X;
import static ru.ganev.xo.model.GameSettings.DEFAULT_DIMENSION;
import static ru.ganev.xo.model.GameSettings.DEFAULT_PLAYERS_COUNT;

public class GameViewer implements View {

    private static final String INCORRECT_CHOICE_MSG = "Incorrect choice";
    private BufferedReader reader;
    private GameSettings gameSettings;

    public GameViewer() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public GameSettings startGameMenu() {
        this.gameSettings = new GameSettings();
        GameMenu.print();
        GameMenu selected = null;
        boolean exit = false;
        while (!exit) {
            try {
                selected = GameMenu.select(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (IncorrectMenuChoice e) {
                out.println("Incorrect menu choice: " + e.getMessage());
            }
            exit = executeMenu(selected);
        }
        return this.gameSettings;
    }

    @Override
    public Coordinate getNextMovement(Figure figure) {
        return null;
    }

    @Override
    public void printExceededMaxCoordinate(String msg) {

    }

    @Override
    public void printAlreadyExist(String msg) {

    }

    private static void printSettingsMenu() {
        out.println("1 - Board size");
        out.println("2 - Players count");
        out.println("3 - Back");
    }

    private boolean executeMenu(GameMenu selected) {
        switch (selected) {
            case PLAY:
                playMenu();
                return true;
            case SETTINGS:
                settingsMenu();
                GameMenu.print();
                return false;
            default:
                exit(0);
        }
        return true;
    }

    private void playMenu() {
        int count = gameSettings.getPlayersCount();
        if (count > 0) {
            enterPlayer(X);
        }
        if (count == 2) {
            enterPlayer(O);
        }
        if (count > 2) {
            throw new RuntimeException("Unsupported players count");
        }
    }

    private void enterPlayer(Figure figure) {
        boolean selected = false;
        String name;
        while (!selected) {
            out.println(String.format("Enter Player %s name", figure.name()));
            try {
                name = reader.readLine();
                gameSettings.addPlayer(figure, new Player(name));
                selected = true;
            } catch (IOException | AlreadySelectedFigure e) {
                //TODO: обработать ситуацию с уже выбанной фигурой
                throw new RuntimeException(e);
            }
        }
    }

    private void settingsMenu() {
        printSettingsMenu();
        boolean exit = false;
        while (!exit) {
            switch (readChoice()) {
                case 1:
                    makeChoice("Please, enter game board size", size -> size < DEFAULT_DIMENSION || size < 0);
                    break;
                case 2:
                    makeChoice("Please, enter players count", count -> count > DEFAULT_PLAYERS_COUNT || count < 0);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
            }
        }
    }

    private void makeChoice(String choiceMsg, Predicate<Integer> incorrectChoice) {
        boolean correctChoice = false;
        while (!correctChoice) {
            out.println(choiceMsg);
            int choice = readChoice();
            if (incorrectChoice.test(choice)) {
                out.println(INCORRECT_CHOICE_MSG);
            } else {
                gameSettings.setPlayersCount(choice);
                out.println("Settings saved");
                correctChoice = true;
            }
        }
        printSettingsMenu();
    }

    private int readChoice() {
        try {
            String choice = reader.readLine();
            return Integer.parseInt(choice);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            out.println(INCORRECT_CHOICE_MSG);
        }
        return -1;
    }
}