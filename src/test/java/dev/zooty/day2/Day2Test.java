package dev.zooty.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @Test
    void getSolution1() {
        assertEquals("1227775554", new Day2().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("4174379265", new Day2().getSolution2());
    }
}