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
        return Optional.of(field.get(coordinate));
    }

    public Optional<Figure> getFigure(int row, int col) {
        return Optional.of(field.get(new Coordinate(row, col)));
    }


    public void setFigure(int row, int col, Figure figure) {
        String error = format("%s %s", row, col);
        if (row > dimension || col > dimension) {
            throw new ExceededMaxCoordinateException(error);
        }
        if (getFigure(row, col).isPresent()) {
            throw new AlreadyExistException(error);
        }
        field.put(new Coordinate(row, col), figure);
    }

    public void setFigure(Coordinate coordinate, Figure figure) {
        setFigure(coordinate.getRow(), coordinate.getCol(), figure);
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
