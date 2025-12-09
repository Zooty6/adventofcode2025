package dev.zooty.day9;

import java.io.BufferedReader;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Cinema {
    private final List<Coordinate> tileMap;
    private final List<Rectangle> border;

    public Cinema(BufferedReader input) {
        tileMap = input.lines()
                .map(this::parseLine)
                .toList();
        border = Stream.concat(
                        IntStream.range(1, tileMap.size())
                                .mapToObj(index -> new Rectangle(tileMap.get(index - 1), tileMap.get(index))),
                        Stream.of(new Rectangle(tileMap.getFirst(), tileMap.getLast())))
                .toList();
    }

    public long getBiggestRedArea() {
        return getAllRectangles()
                .mapToLong(Rectangle::area)
                .max()
                .orElseThrow();
    }

    public long getBiggestRedGreenArea() {
        return getAllRectangles()
                .filter(rectangle -> border.stream()
                        .noneMatch(rectangle::contains))
                .mapToLong(Rectangle::area)
                .max()
                .orElseThrow();
    }

    private Stream<Rectangle> getAllRectangles() {
        return IntStream.range(0, tileMap.size())
                .boxed()
                .flatMap(indexA -> IntStream.range(1, tileMap.size())
                        .mapToObj(indexB -> new Rectangle(tileMap.get((indexA)), tileMap.get(indexB))));
    }

    private Coordinate parseLine(String s) {
        var split = s.split(",");
        return new Coordinate(Long.parseLong(split[0]), Long.parseLong(split[1]));
    }
}
