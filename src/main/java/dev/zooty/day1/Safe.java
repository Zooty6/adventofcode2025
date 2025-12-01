package dev.zooty.day1;

import lombok.Getter;

import java.io.BufferedReader;
import java.util.List;

@Getter
public class Safe {
    private int dial = 50;
    private final List<Rotation> rotations;
    private int numberOfZeroLandings = 0;
    private int numberOfZeroClicks = 0;

    public Safe(BufferedReader inputReader) {
        rotations = inputReader.lines()
                .map(Rotation::new)
                .toList();
    }
    
    public void applyRotations() {
        rotations.forEach(this::applyRotation);
    }
    
    private void applyRotation(Rotation rotation) {
        numberOfZeroClicks += rotation.wouldPassThroughZeroTimes(dial);
        dial = rotation.apply(dial);
        if (dial == 0) {
            numberOfZeroLandings++;
        }
    }
}
