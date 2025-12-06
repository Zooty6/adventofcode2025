package dev.zooty.day6;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.BinaryOperator;

@Getter
@AllArgsConstructor
public enum Operant {
    ADDITION(Long::sum, 0L),
    MULTIPLICATION((a, b) -> ((long) a * b), 1L);

    private final BinaryOperator<Long> operation;
    private final Long identity;

    public static Operant ofCharacter(char c) {
        return switch (c) {
            case '+' -> ADDITION;
            case '*' -> MULTIPLICATION;
            default -> throw new IllegalArgumentException("Invalid character: " + c);
        };
    }

}
