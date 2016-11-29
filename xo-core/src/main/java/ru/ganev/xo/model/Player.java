package ru.ganev.xo.model;

public class Player {

    private final String name;
    private final boolean ai;

    public Player(String name) {
        this(name, false);
    }

    public Player(String name, boolean ai) {
        this.name = name;
        this.ai = ai;
    }

    public String getName() {
        return name;
    }

    public boolean isAi() {
        return ai;
    }
}
