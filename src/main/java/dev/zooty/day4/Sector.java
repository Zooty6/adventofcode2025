package dev.zooty.day4;

public enum Sector {
    ROLL_OF_PAPER,
    FREE;

    public static Sector ofSymbol(char c) {
        return switch (c) {
            case '@' -> ROLL_OF_PAPER;
            case '.' -> FREE;
            default -> throw new IllegalArgumentException("Invalid symbol: " + c);
        };
    }
}
