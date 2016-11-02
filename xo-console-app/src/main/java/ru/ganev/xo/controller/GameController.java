package ru.ganev.xo.controller;

import ru.ganev.xo.model.Game;
import ru.ganev.xo.model.GameSettings;
import ru.ganev.xo.view.View;

public class GameController implements Controller {

    private Game game;
    private GameSettings settings;

    public GameController() {
    }

    @Override
    public void startGame(View view) {
        settings = view.startGameMenu(new GameSettings());
    }
}
