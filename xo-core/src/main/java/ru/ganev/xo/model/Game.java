package ru.ganev.xo.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static ru.ganev.xo.model.Figure.O;
import static ru.ganev.xo.model.Figure.X;
import static ru.ganev.xo.model.Player.createAI;

public class Game {

    private final Map<Figure, Player> players;
    private final Battlefield field;

    private Game(Map<Figure, Player> players, Battlefield field) {
        this.players = players;
        this.field = field;
    }

    public static GameBuilder builder() {
        return new GameBuilder();
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

    public static class GameBuilder {

        private Map<Figure, Player> players = new HashMap<>();
        private Battlefield field;

        private GameBuilder() {
        }

        public GameBuilder player(Player player) {
            players.put(player.getFigure(), player);
            return this;
        }

        public GameBuilder player(Consumer<Player.PlayerBuilder> filler) {
            Player.PlayerBuilder builder = Player.builder();
            filler.accept(builder);
            Player player = builder.build();
            players.put(player.getFigure(), player);
            return this;
        }

        public GameBuilder dimension(int dimension) {
            this.field = new Battlefield(dimension);
            return this;
        }

        public Game build() {
            switch (players.size()) {
                case 0:
                    throw new IllegalArgumentException("No players selected");
                case 1:
                    Optional selected = Optional.of(players.get(X));
                    if (selected.isPresent()) {
                        players.put(O, createAI(O));
                    } else {
                        players.put(X, createAI(X));
                    }
                default:
                    return new Game(players, field);
            }

        }
    }
}

