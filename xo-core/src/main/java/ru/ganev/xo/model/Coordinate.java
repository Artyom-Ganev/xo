package ru.ganev.xo.model;

public class Coordinate implements Comparable<Coordinate> {

    private int row;
    private int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Coordinate o) {
        if (row > o.getRow()) return 1;
        if (row < o.getRow()) return -1;
        if (col > o.getCol()) return 1;
        if (col < o.getCol()) return -1;
        return 0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}
