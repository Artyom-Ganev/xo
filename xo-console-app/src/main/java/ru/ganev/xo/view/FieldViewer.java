package ru.ganev.xo.view;

import ru.ganev.xo.controller.GameController;
import ru.ganev.xo.model.Battlefield;
import ru.ganev.xo.model.Game;

import static java.lang.System.out;

public class FieldViewer {

    public static void startMenu() {
        out.println();
        GameController.newGame();
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

}
