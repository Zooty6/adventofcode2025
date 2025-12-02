package dev.zooty.day2;

import java.util.Arrays;
import java.util.List;

public class GiftShop {
    private final List<IdRanges> idRanges;

    public GiftShop(String input) {
        idRanges = Arrays.stream(input.split(","))
                .map(IdRanges::new)
                .toList();
    }

    public long getNumberOfDoubledIds() {
        return idRanges.stream()
                .parallel()
                .mapToLong(IdRanges::getNumberOfDoubledIdSums)
                .sum();
    }

    public long getNumberOfRepeatedIds() {
        return idRanges.stream()
                .parallel()
                .mapToLong(IdRanges::getNumberOfRepeatedIdSums)
                .sum();
    }
}
