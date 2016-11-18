package ru.ganev.xo.controller;

import java.util.Map;
import java.util.stream.Collectors;

import ru.ganev.xo.exception.AlreadyExistException;
import ru.ganev.xo.exception.ExceededMaxCoordinateException;
import ru.ganev.xo.model.Battlefield;
import ru.ganev.xo.model.Coordinate;
import ru.ganev.xo.model.Figure;
import ru.ganev.xo.model.Game;
import ru.ganev.xo.model.GameSettings;
import ru.ganev.xo.view.View;

import static ru.ganev.xo.model.Figure.O;
import static ru.ganev.xo.model.Figure.X;

public class GameController implements Controller {

    private Game game;
    private Figure current = X;
    private Figure next = O;
    private boolean haveWinner;

    @Override
    public void startGame(View view) {
        GameSettings settings = view.startGameMenu(new GameSettings());
        game = new Game(settings.getPlayers(), new Battlefield(settings.getDimension()));
        while (haveWinner) {
            Coordinate nextMovement = view.getNextMovement(next);
            try {
                game.getField().setFigure(nextMovement, next);
                updateCurrentAndNext();
                checkWinner();
            } catch (ExceededMaxCoordinateException e) {
                view.printExceededMaxCoordinate(e.getMessage());
            } catch (AlreadyExistException e) {
                view.printAlreadyExist(e.getMessage());
            }
        }
    }

    private void updateCurrentAndNext() {
        final Figure tmp = next;
        next = current;
        current = tmp;
    }

    private void checkWinner() {
        Map<Coordinate, Figure> currentState = game.getField().getCurrentState();
        Map<Coordinate, Figure> sameCoordinates = currentState.entrySet().stream()
                .filter(e -> e.getKey().getCol() == e.getKey().getRow())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        haveWinner = false;
    }
}
