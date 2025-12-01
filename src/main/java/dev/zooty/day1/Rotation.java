package dev.zooty.day1;

public class Rotation {
    private final int distance;
    private final Direction direction;

    public Rotation(String line) {
       direction = Direction.ofCharacter(line.charAt(0)); 
       distance = Integer.parseInt(line.substring(1));
    }

    public int apply(int dial) {
        return switch (direction) {
            case RIGHT -> (dial + distance) % 100;
            case LEFT -> {
                int newDistance = dial - distance;
                yield  newDistance < 0 ? (100 - (Math.abs(newDistance % 100))) % 100 : newDistance % 100;
            }
        }; 
    }

    public int wouldPassThroughZeroTimes(int dial) {
        return switch (direction) {
            case RIGHT -> (dial + distance) / 100;
            case LEFT -> (dial - distance <= 0 ? 1 : 0) + Math.abs((dial - distance) / 100) - (dial == 0 ? 1 : 0);
        };
    }
}
