package ru.ganev.xo.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ru.ganev.xo.controller.GameController;
import ru.ganev.xo.model.Battlefield;
import ru.ganev.xo.model.Figure;
import ru.ganev.xo.model.Game;
import ru.ganev.xo.model.Player;
import ru.ganev.xo.view.menu.Menu;
import ru.ganev.xo.view.menu.MenuLine;

import static java.lang.System.out;
import static ru.ganev.xo.model.Figure.O;
import static ru.ganev.xo.model.Figure.X;

public class FieldViewer {

    private static final int DEFAULT_FIELD_SIZE = 3;
    private static final int DEFAULT_PLAYERS_COUNT = 1;
    private BufferedReader reader;
    private Game.GameBuilder gameBuilder;
    private int playersCount;

    public FieldViewer() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.gameBuilder = Game.builder().dimension(DEFAULT_FIELD_SIZE);
        this.playersCount = DEFAULT_PLAYERS_COUNT;
    }

    public static void redrow(Game game) {
        Battlefield field = game.getField();
        field.getCurrentState().entrySet().forEach(e -> {
            out.print(String.format("| %s ", e.getValue()));
            if (e.getKey().getCol() == field.getDimension() - 1) {
                out.println("|");
            }
        });
    }

    private void newGameMenu() {
        Menu.builder()
                .entry(new MenuLine("1 - Start Game") {
                    @Override
                    public void execute() {
                        for (int i = 0; i < playersCount; i++) {
                            playerSettings(i);
                        }
                        GameController.newGame(gameBuilder.build());
                    }
                })
                .entry(new MenuLine("2 - Settings") {
                    @Override
                    public void execute() {
                        out.println();
                        gameSettingsMenu();
                    }
                })
                .build()
                .execute();
    }

    private void gameSettingsMenu() {
        Menu.builder()
                .entry(new MenuLine("1 - Battlefield size") {
                    @Override
                    public void execute() {
                        try {
                            out.println("Enter field size");
                            String line = reader.readLine();
                            int choice = Integer.parseInt(line);
                            gameBuilder.dimension(choice);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (NumberFormatException e) {
                            out.println();
                            out.println("Please enter correct size");
                        } finally {
                            out.println("Settings saved");
                            out.println();
                            gameSettingsMenu();
                        }
                    }
                })
                .entry(new MenuLine("2 - Players count") {
                    @Override
                    public void execute() {
                        out.println();
                        playersCountMenu();
                    }
                })
                .entry(new MenuLine("3 - Back") {
                    @Override
                    public void execute() {
                        out.println();
                        newGameMenu();
                    }
                })
                .build()
                .execute();
    }

    private void playersCountMenu() {
        Menu.builder()
                .entry(new MenuLine("1 - One player") {
                    @Override
                    public void execute() {
                        playersCount = 1;
                        out.println("Settings saved");
                        out.println();
                        gameSettingsMenu();
                    }
                })
                .entry(new MenuLine("2 - Players count") {
                    @Override
                    public void execute() {
                        playersCount = 2;
                        out.println("Settings saved");
                        out.println();
                        gameSettingsMenu();
                    }
                })
                .build()
                .execute();
    }

    public void playerSettings(int i) {
        out.println(String.format("Insert Player%s name", i));
        try {
            String name = reader.readLine();
            Figure figure = null;
            while (figure == null) {
                out.println();
                out.println(String.format("Select Player%s figure (X or O)", i));
                String choice = reader.readLine();
                switch (choice) {
                    case "x":
                    case "X":
                        figure = X;
                        break;
                    case "0":
                    case "o":
                    case "O":
                        figure = O;
                        break;
                    default:
                        out.println();
                        out.println("Incorrect choice. Please select X or O");
                }
            }
            gameBuilder.player(Player.builder()
                    .name(name)
                    .figure(figure)
                    .build(), figure);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void startMenu() {
        out.println();
        Menu.builder()
                .entry(new MenuLine("1 - New game") {
                    @Override
                    public void execute() {
                        out.println();
                        newGameMenu();
                    }
                })
                .build()
                .execute();
    }

}
