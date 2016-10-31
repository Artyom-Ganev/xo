package ru.ganev.xo.model;

public class Player {

    private final String name;
    private final boolean isAI;
    private final Figure figure;

    private Player(String name, boolean isAI, Figure figure) {
        this.name = name;
        this.isAI = isAI;
        this.figure = figure;
    }

    public static PlayerBuilder builder() {
        return new PlayerBuilder();
    }

    public static Player createAI(Figure figure) {
        return builder()
                .name("AI")
                .figure(figure)
                .isAI(true)
                .build();
    }

    public String getName() {
        return name;
    }

    public boolean isAI() {
        return isAI;
    }

    public Figure getFigure() {
        return figure;
    }

    public static final class PlayerBuilder {

        private String name;
        private boolean isAI;
        private Figure figure;

        private PlayerBuilder() {
        }

        public PlayerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PlayerBuilder isAI(boolean isAI) {
            this.isAI = isAI;
            return this;
        }

        public PlayerBuilder figure(Figure figure) {
            this.figure = figure;
            return this;
        }

        public Player build() {
            return new Player(name, isAI, figure);
        }
    }
}
