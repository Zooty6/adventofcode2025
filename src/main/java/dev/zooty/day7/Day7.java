package dev.zooty.day7;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day7 implements Day {
    private final int day = 7;
    private final TachyonManifold tachyonManifold;

    public Day7() {
        tachyonManifold = new TachyonManifold(getInputReader());
        tachyonManifold.beam();
    }

    @Override
    public String getSolution1() {
        return String.valueOf(tachyonManifold.getAmountOfSplits());
    }

    @Override
    public String getSolution2() {
        return String.valueOf(tachyonManifold.getNumberOfTimelines());
    }
}
