package dev.zooty.day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {

    @Test
    void getSolution1() {
        assertEquals("40", new Day8() {
            @Override
            int getConnectionLimit() {
                return 10;
            }
        }.getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("25272", new Day8().getSolution2());
    }
}