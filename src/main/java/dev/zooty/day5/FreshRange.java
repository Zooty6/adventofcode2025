package dev.zooty.day5;

import lombok.Data;

@Data
public class FreshRange {
    private long start;
    private long end;
    
    public FreshRange(String input) {
        String[] split = input.split("-");
        start = Long.parseLong(split[0]); 
        end = Long.parseLong(split[1]);
    }

    public boolean isIn(Ingredient ingredient) {
        return start <= ingredient.id() && ingredient.id() <= end;
    }
}
