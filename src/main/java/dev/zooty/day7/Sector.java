package dev.zooty.day7;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public sealed class Sector {
    private Sector() {}

    public static final class Starting extends Sector {}

    public static final class Free extends Sector {}

    public static final class Splitter extends Sector {}

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Beam extends Sector {
        private long timelines = 1L;

        public Beam(Sector sectorAbove, Sector sector) {
            if (sectorAbove instanceof Beam beamAbove) {
                long timelinesFromOthers = beamAbove.getTimelines();
                if (sector instanceof Beam beamOn) {
                    timelinesFromOthers += beamOn.getTimelines();
                }
                this.timelines = timelinesFromOthers;
            }
        }
    }

    public static Sector ofCharacter(char c) {
        return switch (c) {
            case 'S' -> new Starting();
            case '.' -> new Free();
            case '^' -> new Splitter();
            case '|' -> new Beam();
            default -> throw new IllegalArgumentException("Invalid symbol: " + c);
        };
    }

    public static Sector ofCharacter(int c) {
        return ofCharacter((char) c);
    }
}