package ru.ganev.xo.model;

import java.util.HashMap;
import java.util.Map;

import ru.ganev.xo.exception.AlreadySelectedFigure;

public final class GameSettings {

    public static final int DEFAULT_PLAYERS_COUNT = 2;
    public static final int DEFAULT_DIMENSION = 3;
    private Map<Figure, Player> players;
    private int dimension;
    private int playersCount;

    public GameSettings() {
        this.dimension = DEFAULT_DIMENSION;
        this.playersCount = DEFAULT_PLAYERS_COUNT;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    public Map<Figure, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<Figure, Player> players) {
        this.players = players;
    }

    public void addPlayer(Figure figure, Player player) throws AlreadySelectedFigure {
        assert figure != null && player != null;
        if (players == null) {
            players = new HashMap<>();
        }
        if (players.containsKey(figure)) {
            throw new AlreadySelectedFigure(figure.name());
        }
        players.put(figure, player);
    }
}
