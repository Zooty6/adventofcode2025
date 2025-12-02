package dev.zooty.day2;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day2 implements Day {
    private final int day = 2;

    @Override
    public String getSolution1() {
        var giftShop = new GiftShop(getInputString());
        return String.valueOf(giftShop.getNumberOfDoubledIds());
    }

    @Override
    public String getSolution2() {
        var giftShop = new GiftShop(getInputString());
        return String.valueOf(giftShop.getNumberOfRepeatedIds());
    }
}
