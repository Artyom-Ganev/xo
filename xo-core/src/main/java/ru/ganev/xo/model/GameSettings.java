package ru.ganev.xo.model;

import ru.ganev.xo.exception.AlreadySelectedFigure;

import java.util.HashMap;
import java.util.Map;

public class GameSettings {

    public static int DEFAULT_PLAYERS_COUNT = 2;
    public static int DEFAULT_DIMENSION = 3;
    private Map<Figure, Player> players;
    private int dimension;
    private int playersCount;

    public GameSettings() {
        dimension = DEFAULT_DIMENSION;
        playersCount = DEFAULT_PLAYERS_COUNT;
    }

    public int getDimension() {
        return dimension;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    public void addPlayer(Figure figure, Player player) throws AlreadySelectedFigure {
        assert (figure != null && player != null);
        if (players == null) {
            players = new HashMap<>();
        }
        if (players.containsKey(figure)) {
            throw new AlreadySelectedFigure(figure.name());
        }
        players.put(figure, player);
    }
}
