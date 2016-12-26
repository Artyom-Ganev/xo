package ru.ganev.xo;

import ru.ganev.xo.controller.GameController;
import ru.ganev.xo.view.GameViewer;

public class ConsoleApplication {

    public static void main(String[] args) {
        GameController controller = new GameController(new GameViewer());
        controller.startGame();
    }
}
