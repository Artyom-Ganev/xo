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
    private Figure current = O;
    private Figure next = X;
    private boolean haveWinner;
    private View view;

    public GameController(View view) {
        this.view = view;
    }

    @Override
    public void startGame() {
        GameSettings settings = view.startGameMenu();
        game = new Game(settings.getPlayers(), settings.getDimension());
        while (!haveWinner) {
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
        view.printCurrentState(game.getField());
        Map<Coordinate, Figure> currentState = game.getField().getCurrentState();
//        Map<Coordinate, Figure> sameCoordinates = currentState.entrySet().stream()
//                .filter(e -> e.getKey().getCol() == e.getKey().getRow())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        haveWinner = true;
    }
}
