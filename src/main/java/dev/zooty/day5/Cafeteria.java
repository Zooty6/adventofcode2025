package dev.zooty.day5;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Cafeteria {
    private final List<FreshRange> freshRanges;
    private final List<Ingredient> ingredients;

    public Cafeteria(BufferedReader inputReader) {
        freshRanges = parseFreshRanges(inputReader);
        ingredients = parseIngredients(inputReader);
    }

    @SneakyThrows
    private List<FreshRange> parseFreshRanges(BufferedReader inputReader) {
        String nextLine = inputReader.readLine();
        ArrayList<FreshRange> result = new ArrayList<>();
        while (!nextLine.isEmpty()) {
            result.add(new FreshRange(nextLine));
            nextLine = inputReader.readLine();
        }
        return result;
    }

    @SneakyThrows
    private List<Ingredient> parseIngredients(BufferedReader inputReader) {
        String nextLine = inputReader.readLine();
        ArrayList<Ingredient> result = new ArrayList<>();
        while (nextLine != null) {
            result.add(new Ingredient(Long.parseLong(nextLine)));
            nextLine = inputReader.readLine();
        }
        return result;
    }

    public long getAvailableIngredientCount() {
        return ingredients.parallelStream()
                .filter(ingredient -> freshRanges.parallelStream()
                        .anyMatch(freshRange -> freshRange.isIn(ingredient)))
                .count();
    }

    public long getAllAvailableIngredientCount() {
        final List<FreshRange> countedRanges = new ArrayList<>();
        freshRanges.forEach(freshRange -> checkOverlapsAndAdd(countedRanges, freshRange));
        return countedRanges.stream()
                .mapToLong(range -> range.getEnd() - range.getStart() + 1)
                .sum();
    }

    private void checkOverlapsAndAdd(List<FreshRange> countedRanges, FreshRange rangeToAdd) {
        for (int i = countedRanges.size() - 1; i >= 0; i--) {
            FreshRange countedRange = countedRanges.get(i);
            if (contains(countedRange, rangeToAdd)) {
                return;
            } else if (contains(rangeToAdd, countedRange)) {
                countedRanges.remove(i);
            } else if (overlapsStart(countedRange, rangeToAdd)) {
                rangeToAdd.setStart(countedRange.getEnd() + 1);
            } else if (overlapsStart(rangeToAdd, countedRange)) {
                rangeToAdd.setEnd(countedRange.getStart() - 1);
            }
        }
            countedRanges.add(rangeToAdd);
    }

    /**
     * rangeToCompare: __o------------o<br>
     * rangeToCheck: __________o------------o
     */
    private boolean overlapsStart(FreshRange rangeToCompare, FreshRange rangeToCheck) {
        return rangeToCompare.getStart() <= rangeToCheck.getStart() && rangeToCompare.getEnd() >= rangeToCheck.getStart();
    }

    private boolean contains(FreshRange rangeToCompare, FreshRange rangeToCheck) {
        return rangeToCompare.getStart() <= rangeToCheck.getStart() && rangeToCompare.getEnd() >= rangeToCheck.getEnd();
    }
}
