package dev.zooty.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    @Test
    void getSolution1() {
        assertEquals("4277556", new Day6().getSolution1());
    }

    @Test
    void getSolution2() {
        // 1058 + 3253600 + 625 + 8544 = 3263827
        assertEquals("3263827", new Day6().getSolution2());
    }
}