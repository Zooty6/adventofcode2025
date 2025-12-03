package dev.zooty.day3;

import java.io.BufferedReader;
import java.util.List;

public class Escalator {
    private final List<BatteryBank> batteryBankList;

    public Escalator(BufferedReader inputReader) {
        batteryBankList = inputReader.lines()
                .map(BatteryBank::new)
                .toList();
    }

    public long getTotalOutputOfMaxJoltage(int batteryBankSize) {
        return batteryBankList.stream()
                .mapToLong(batteryBank -> batteryBank.getMaxJoltage(batteryBankSize))
                .sum();
    }
}
