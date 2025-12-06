package dev.zooty.day6;

import java.util.ArrayList;
import java.util.List;

public class HomeWork {
    protected final List<Operation> operations = new ArrayList<>();

    public long doHomework() {
        return operations.parallelStream()
                .mapToLong(Operation::doOperation)
                .sum();
    }
}
