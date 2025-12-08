package dev.zooty.day8;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day8 implements Day {
    private final int day = 8;

    int getConnectionLimit() {
        return 1000;
    }

    @Override
    public String getSolution1() {
        return String.valueOf(new Playground(getInputReader(), getConnectionLimit()).getThreeLargestCircuits());
    }

    @Override
    public String getSolution2() {
        return String.valueOf(new Playground(getInputReader(), getConnectionLimit()).getLastTwoBoxesValue());
    }
}
