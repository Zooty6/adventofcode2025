package dev.zooty.day2;

import lombok.Getter;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Getter
public class IdRanges {
    private final long startId;
    private final long endId;

    public IdRanges(String input) {
        var split = input.split("-");
        startId = Long.parseLong(split[0]);
        endId = Long.parseLong(split[1]);
    }

    public long getNumberOfDoubledIdSums() {
        return LongStream.rangeClosed(startId, endId)
                .parallel()
                .filter(this::isDoubled)
                .sum();
    }

    public long getNumberOfRepeatedIdSums() {
        return LongStream.rangeClosed(startId, endId)
                .parallel()
                .filter(this::isRepeated)
                .sum();
    }

    private boolean isDoubled(long id) {
        String idString = String.valueOf(id);
        return idString.substring(0, idString.length() / 2).equals(idString.substring(idString.length() / 2));
    }

    private boolean isRepeated(long id) {
        String idString = String.valueOf(id);
        return IntStream.range(1, idString.length() / 2 + 1)
                .boxed()
                .anyMatch(subStringLength -> isOnlyRepeating(subStringLength, idString));
    }

    private boolean isOnlyRepeating(Integer subStringLength, String aString) {
        return aString
                .replaceAll(aString.substring(0, subStringLength), "")
                .isEmpty();
    }
}
