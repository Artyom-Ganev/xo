package ru.ganev.xo.view;

import ru.ganev.xo.model.Coordinate;
import ru.ganev.xo.model.Figure;
import ru.ganev.xo.model.GameSettings;

public interface View {

    GameSettings startGameMenu(GameSettings gameSettings);

    Coordinate getNextMovement(Figure figure);

    void printExceededMaxCoordinate(String msg);

    void printAlreadyExist(String msg);
}
