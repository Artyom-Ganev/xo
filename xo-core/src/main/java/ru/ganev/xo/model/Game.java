package ru.ganev.xo.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import static ru.ganev.xo.model.Figure.O;
import static ru.ganev.xo.model.Figure.X;

public class Game {

    private Map<Figure, Player> players;
    private final Battlefield field;

    public Game(Map<Figure, Player> players, int dimension) {
        this.players = players;
        check();
        this.field = new Battlefield(dimension);
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

    private void check() {
        if (players == null) {
            players = new EnumMap<>(Figure.class);
        }
        final int size = players.size();
        Player ai = new Player("AI", true);
        if (size == 0) {
            players.put(O, ai);
            players.put(X, ai);
        }
        if (size == 1) {
            Optional selected = Optional.of(players.get(X));
            if (selected.isPresent()) {
                players.put(O, ai);
            } else {
                players.put(X, ai);
            }
        }
    }
}

