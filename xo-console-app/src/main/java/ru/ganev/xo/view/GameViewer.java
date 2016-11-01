package ru.ganev.xo.view;

import ru.ganev.xo.model.GameSettings;
import ru.ganev.xo.view.menu.GameMenu;

public class GameViewer implements View {

    @Override
    public GameSettings gameMenu(GameSettings gameSettings) {
        GameMenu.print();

        return gameSettings;
    }
}
