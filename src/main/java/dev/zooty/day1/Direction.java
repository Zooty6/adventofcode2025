package dev.zooty.day1;

public enum Direction {
    LEFT, 
    RIGHT;
    
    public static Direction ofCharacter(char character) {
        return switch (character) {
            case 'L' -> LEFT;
            case 'R' -> RIGHT;
            default -> throw new IllegalArgumentException("Invalid character: " + character);
        };
    }
}
