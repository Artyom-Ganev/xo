package ru.ganev.xo.model;

import java.util.Map;
import java.util.Optional;

import static ru.ganev.xo.model.Figure.O;
import static ru.ganev.xo.model.Figure.X;

public class Game {

    private final Map<Figure, Player> players;
    private final Battlefield field;
    private Figure current;

    private Game(Map<Figure, Player> players, Battlefield field) {
        this.players = players;
        check();
        this.field = field;
    }

    public Player getPlayer1() {
        return players.get(X);
    }

    public Player getPlayer2() {
        return players.get(O);
    }

    public Battlefield getField() {
        return field;
    }

    public Figure getCurrent() {
        return current;
    }

    public void setCurrent(Figure current) {
        this.current = current;
    }

    private void check() {
        switch (players.size()) {
            case 0:
                throw new IllegalArgumentException("No players selected");
            case 1:
                Optional selected = Optional.of(players.get(X));
                Player ai = new Player("AI", true);
                if (selected.isPresent()) {
                    players.put(O, ai);
                } else {
                    players.put(X, ai);
                }
        }

    }
}

