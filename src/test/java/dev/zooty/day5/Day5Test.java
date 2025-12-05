package dev.zooty.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    @Test
    void getSolution1() {
        assertEquals("3", new Day5().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("14", new Day5().getSolution2());
    }
}