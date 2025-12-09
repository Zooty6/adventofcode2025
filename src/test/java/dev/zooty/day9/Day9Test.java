package dev.zooty.day9;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    @Test
    void getSolution1() {
        assertEquals("50", new Day9().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("24", new Day9().getSolution2());
    }

    @Test
    void getSolution2WithRedditDataset1() {
        /* source: https://old.reddit.com/r/adventofcode/comments/1pi36pq/2025_day_9_part_2_more_examples_to_soften_your/
        ...............
        ...##########..
        ...#........#..
        ...#...######..
        ...#...#.......
        ...#...####....
        ...#......#....
        ...#......#....
        ...#......#....
        ...########....
        ...............
         */
        Day9 day9 = new Day9() {
            @Override
            public BufferedReader getInputReader() {
                return new BufferedReader(Reader.of("""
                        4,2
                        13,2
                        13,4
                        8,4
                        8,6
                        11,6
                        11,10
                        4,10"""));
            }
        };
        assertEquals("40", day9.getSolution2());
    }

    @Test
    void getSolution2WithRedditDataset2() {
        /* source: https://old.reddit.com/r/adventofcode/comments/1pi36pq/2025_day_9_part_2_more_examples_to_soften_your/
        ...............
        ..###########..
        ..#.........#..
        ..#....######..
        ..#....#.......
        ..#....####....
        ..#.......#....
        ..#.###...#....
        ..#.#.#...#....
        ..###.#...#....
        ......#####....
        ...............
         */
        Day9 day9 = new Day9() {
            @Override
            public BufferedReader getInputReader() {
                return new BufferedReader(Reader.of("""
                        3,2
                        13,2
                        13,4
                        8,4
                        8,6
                        11,6
                        11,11
                        7,11
                        7,8
                        5,8
                        5,10
                        3,10"""));
            }
        };
        assertEquals("35", day9.getSolution2());
    }

    @Test
    void getSolution2WithRedditDataset3() {
        /* source: https://old.reddit.com/r/adventofcode/comments/1pi36pq/2025_day_9_part_2_more_examples_to_soften_your/
        .....................
        ..###############....
        ..#.............#....
        ..#.............#....
        ..####..........#....
        .....#..........#....
        .....#..........#....
        .....#....#####.#....
        .....#....#...#.#....
        .....#....#...#.#....
        .....#....#.###.#....
        ...###....#.#...#....
        ...#......#.#####....
        ...#......#..........
        ...#......########...
        ...#.............#...
        ...###############...
        .....................
         */
        Day9 day9 = new Day9() {
            @Override
            public BufferedReader getInputReader() {
                return new BufferedReader(Reader.of("""
                        3,2
                        17,2
                        17,13
                        13,13
                        13,11
                        15,11
                        15,8
                        11,8
                        11,15
                        18,15
                        18,17
                        4,17
                        4,12
                        6,12
                        6,5
                        3,5"""));
            }
        };
        assertEquals("66", day9.getSolution2());
    }
}