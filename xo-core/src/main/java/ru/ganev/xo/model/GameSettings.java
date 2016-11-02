package ru.ganev.xo.model;

import java.util.HashMap;
import java.util.Map;

import ru.ganev.xo.exception.AlreadySelectedFigure;

public class GameSettings implements Cloneable {

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

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    public void setPlayers(Map<Figure, Player> players) {
        this.players = players;
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

    @Override
    public GameSettings clone() {
        try {
            GameSettings newSettings = (GameSettings) super.clone();
            newSettings.players = this.players;
            newSettings.dimension = this.dimension;
            newSettings.playersCount = this.playersCount;
            return newSettings;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
