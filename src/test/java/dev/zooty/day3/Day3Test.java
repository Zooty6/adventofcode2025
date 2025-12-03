package dev.zooty.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

    @Test
    void getSolution1() {
        assertEquals("357", new Day3().getSolution1());
    }

    @Test
    void getSolution2() {
        // 987654321111 + 811111111119 + 434234234278 + 888911112111 = 3121910778619
        assertEquals("3121910778619", new Day3().getSolution2());
    }
}