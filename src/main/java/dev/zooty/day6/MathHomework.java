package dev.zooty.day6;

import lombok.SneakyThrows;

import java.io.BufferedReader;

public class MathHomework extends HomeWork {

    @SneakyThrows
    public MathHomework(BufferedReader inputReader) {
        String nextLine = inputReader.readLine();

        while (nextLine.charAt(0) != '*' && nextLine.charAt(0) != '+') {
            addArguments(nextLine);
            nextLine = inputReader.readLine();
        }
        addOperants(nextLine);
    }

    private void addArguments(String nextLine) {
        nextLine = nextLine.trim();
        String[] split = nextLine.split("\\s+");
        for (int i = 0; i < split.length; i++) {
            if (i >= operations.size()) {
                operations.add(new Operation());
            }
            Operation operation = operations.get(i);
            operation.getArguments().add(Long.parseLong(split[i]));
        }
    }

    private void addOperants(String nextLine) {
        nextLine = nextLine.trim();
        String[] split = nextLine.split("\\s+");
        for (int i = 0; i < split.length; i++) {
            operations.get(i).setOperant(Operant.ofCharacter(split[i].charAt(0)));
        }
    }
}
