package dev.zooty.day3;

import lombok.Getter;

import java.util.List;
import java.util.stream.IntStream;

@Getter
public class BatteryBank {
    private final List<Integer> joltages;

    public BatteryBank(String input) {
        joltages = input.chars().mapToObj(i -> i - '0').toList();
    }

    public long getMaxJoltage(int size) {
        int highestIndex = -1;
        long joltage = 0;
        for (; size > 0; size--) {
            highestIndex = findHighestIndex(highestIndex + 1, size);
            joltage = joltage * 10L + joltages.get(highestIndex);
        }
        return joltage;
    }

    private int findHighestIndex(int starting, int upUntil) {
        return IntStream.range(starting, joltages.size() - upUntil + 1)
                .boxed()
                .max((index1, index2) -> joltages.get(index1).compareTo(joltages.get(index2)))
                .orElseThrow();
    }

}
