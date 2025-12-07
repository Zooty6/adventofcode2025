package dev.zooty.day7;

import dev.zooty.day7.Sector.Beam;
import dev.zooty.day7.Sector.Splitter;
import dev.zooty.day7.Sector.Starting;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TachyonManifold {
    private final List<List<Sector>> manifold;

    public TachyonManifold(BufferedReader inputReader) {
        manifold = inputReader.lines()
                .map(this::parseLine)
                .toList();
    }

    public void beam() {
        IntStream.range(1, manifold.size())
                .forEach(rowIndex -> IntStream.range(0, manifold.get(rowIndex).size())
                        .forEach(columnIndex -> beam(rowIndex, columnIndex)));
    }

    public long getAmountOfSplits() {
        return IntStream.range(1, manifold.size())
                .mapToLong(rowIndex -> IntStream.range(0, manifold.get(rowIndex).size())
                        .filter(columnIndex -> manifold.get(rowIndex).get(columnIndex) instanceof Splitter)
                        .filter(columnIndex -> manifold.get(rowIndex - 1).get(columnIndex) instanceof Beam)
                        .count())
                .sum();
    }

    public long getNumberOfTimelines() {
        return manifold.getLast()
                .stream()
                .mapToLong(sector -> sector instanceof Beam beam ? beam.getTimelines() : 0)
                .sum();
    }

    private List<Sector> parseLine(String line) {
        return new ArrayList<>(line.chars()
                .mapToObj(Sector::ofCharacter)
                .toList());
    }

    private void beam(int rowIndex, int columnIndex) {
        Sector sectorAt = manifold.get(rowIndex).get(columnIndex);
        Sector sectorAbove = manifold.get(rowIndex - 1).get(columnIndex);
        if (sectorAbove instanceof Beam || sectorAbove instanceof Starting) {
            if (!(sectorAt instanceof Splitter)) {
                manifold.get(rowIndex).set(columnIndex, new Beam(sectorAbove, sectorAt));
            } else {
                manifold.get(rowIndex).set(columnIndex - 1, new Beam(sectorAbove, manifold.get(rowIndex).get(columnIndex - 1)));
                manifold.get(rowIndex).set(columnIndex + 1, new Beam(sectorAbove, manifold.get(rowIndex).get(columnIndex + 1)));
            }
        }
    }
}
