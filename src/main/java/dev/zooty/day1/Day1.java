package dev.zooty.day1;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day1 implements Day {
    private final int day = 1;

    @Override
    public String getSolution1() {
        Safe safe = new Safe(getInputReader());
        safe.applyRotations();
        return String.valueOf(safe.getNumberOfZeroLandings());
    }

    @Override
    public String getSolution2() {
        Safe safe = new Safe(getInputReader());
        safe.applyRotations();
        return String.valueOf(safe.getNumberOfZeroClicks());
    }
}
