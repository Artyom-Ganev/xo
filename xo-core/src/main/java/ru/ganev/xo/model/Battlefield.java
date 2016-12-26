package ru.ganev.xo.model;

import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import ru.ganev.xo.exception.AlreadyExistException;
import ru.ganev.xo.exception.ExceededMaxCoordinateException;

import static java.lang.String.format;

public class Battlefield {

    private int dimension;
    private SortedMap<Coordinate, Figure> field;

    public Battlefield(int dimension) {
        this.dimension = dimension;
        this.field = new TreeMap<>(Coordinate::compareTo);
        fillEmptyCoordinates();
    }

    public Optional<Figure> getFigure(Coordinate coordinate) {
        return Optional.ofNullable(field.get(coordinate));
    }

    public Optional<Figure> getFigure(int row, int col) {
        return getFigure(new Coordinate(row, col));
    }

    public void setFigure(Coordinate coordinate, Figure figure) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        String error = format("%s %s", row, col);
        if (row > dimension || col > dimension) {
            throw new ExceededMaxCoordinateException(error);
        }
        if (getFigure(coordinate).isPresent()) {
            throw new AlreadyExistException(error);
        }
        field.put(coordinate, figure);
    }

    public int getDimension() {
        return dimension;
    }

    public TreeMap<Coordinate, Figure> getCurrentState() {
        return new TreeMap<>(field);
    }

    private void fillEmptyCoordinates() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                field.put(new Coordinate(i, j), null);
            }
        }
    }
}
