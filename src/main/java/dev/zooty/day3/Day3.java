package dev.zooty.day3;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day3 implements Day {
    private final int day = 3;

    @Override
    public String getSolution1() {
        return String.valueOf(new Escalator(getInputReader()).getTotalOutputOfMaxJoltage(2));
    }

    @Override
    public String getSolution2() {
        return String.valueOf(new Escalator(getInputReader()).getTotalOutputOfMaxJoltage(12));
    }
}
