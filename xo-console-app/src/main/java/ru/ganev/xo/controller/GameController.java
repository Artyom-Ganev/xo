package ru.ganev.xo.controller;

import ru.ganev.xo.model.Game;
import ru.ganev.xo.model.Player;
import ru.ganev.xo.view.FieldViewer;

import static ru.ganev.xo.model.Figure.O;
import static ru.ganev.xo.model.Figure.X;

public class GameController {

    public static void newGame(Game game) {
        FieldViewer.redrow(game);
    }

}
