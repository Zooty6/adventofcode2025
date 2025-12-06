package dev.zooty.day6;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day6 implements Day {
    private final int day = 6;
    private final MathHomework mathHomework = new MathHomework(getInputReader(), getInputReader());

    @Override
    public String getSolution1() {
        return String.valueOf(mathHomework.doHomework());
    }

    @Override
    public String getSolution2() {
        return String.valueOf(mathHomework.doShittyHomework());
    }
}
