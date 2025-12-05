package dev.zooty.day4;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrintingDepartment {
    private record NeighborCountWithCoordinate(Coordinate coordinate, int numberOfNeighbors) {}

    private record SectorWithCoordinate(Coordinate coordinate, Sector sector) {}

    private final List<List<Sector>> map;

    public PrintingDepartment(BufferedReader inputReader) {
        map = inputReader.lines()
                .map(this::parseLine)
                .toList();
    }

    public long getNumberOfMovableWraps() {
        return getMovableNeighbors()
                .count();
    }

    public int getNumberOfMoves() {
        List<NeighborCountWithCoordinate> toMove;
        int count = 0;
        do {
            toMove = getMovableNeighbors().toList();
            toMove.forEach(this::moveWraps);
            count += toMove.size();
        } while (!toMove.isEmpty());

        return count;
    }

    private List<Sector> parseLine(String line) {
        return new ArrayList<>(line.chars()
                .mapToObj(c -> Sector.ofSymbol((char) c))
                .toList());
    }

    private Stream<NeighborCountWithCoordinate> getMovableNeighbors() {
        return IntStream.range(0, map.size())
                .parallel()
                .boxed()
                .flatMap(rowIndex -> IntStream.range(0, map.get(rowIndex).size())
                        .filter(columnIndex -> map.get(rowIndex).get(columnIndex) == Sector.ROLL_OF_PAPER)
                        .mapToObj(columnIndex -> getNumberOfNeighbors(rowIndex, columnIndex)))
                .filter(numberOfNeighbors -> numberOfNeighbors.numberOfNeighbors() < 4);
    }

    private NeighborCountWithCoordinate getNumberOfNeighbors(int rowIndex, int columnIndex) {
        return new NeighborCountWithCoordinate(new Coordinate(rowIndex, columnIndex), (int) IntStream.rangeClosed(rowIndex - 1, rowIndex + 1)
                .boxed()
                .flatMap(row -> IntStream.rangeClosed(columnIndex - 1, columnIndex + 1)
                        .mapToObj(column -> new Coordinate(row, column)))
                .filter(coordinate -> rowIndex != coordinate.x() || columnIndex != coordinate.y())
                .map(this::getSector)
                .filter(sector -> sector.sector() == Sector.ROLL_OF_PAPER)
                .count());
    }

    private SectorWithCoordinate getSector(Coordinate coordinate) {
        try {
            return new SectorWithCoordinate(coordinate, map.get(coordinate.x()).get(coordinate.y()));
        } catch (IndexOutOfBoundsException _) {
            return new SectorWithCoordinate(coordinate, Sector.FREE);
        }
    }

    private void moveWraps(NeighborCountWithCoordinate neighborCountWithCoordinate) {
        try {
            map.get(neighborCountWithCoordinate.coordinate().x()).set(neighborCountWithCoordinate.coordinate().y(), Sector.FREE);
        } catch (IndexOutOfBoundsException _) {
            // do nothing
        }
    }
}
