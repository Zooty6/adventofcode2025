package dev.zooty.day6;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Operation {
    private final List<Long> arguments = new ArrayList<>();
    private Operant operant = null;

    public long doOperation() {
        return arguments
                .parallelStream()
                .reduce(operant.getIdentity(), operant.getOperation());
    }
}
