package dev.zooty.day6;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class CephalopodMathHomework extends HomeWork {

    @SneakyThrows
    public CephalopodMathHomework(BufferedReader inputReader) {
        List<String> lines = inputReader.readAllLines();
        ArrayList<Long> arguments = new ArrayList<>();
        for (int i = lines.getFirst().length() - 1; i >= 0; i--) {
            StringBuilder argumentBuilder = new StringBuilder();
            Operant operant = null;
            for (String line : lines) {
                char entry = line.charAt(i);
                if (Character.isDigit(entry)) {
                    argumentBuilder.append(entry);
                } else if (entry == '+' || entry == '*') {
                    operant = Operant.ofCharacter(entry);
                }
            }
            if (!argumentBuilder.isEmpty()) {
                arguments.add(Long.parseLong(argumentBuilder.toString()));
            }
            addOperation(operant, arguments);
        }
    }

    private void addOperation(Operant operant, ArrayList<Long> arguments) {
        if (operant != null) {
            Operation operation = new Operation();
            operation.getArguments().addAll(arguments);
            operation.setOperant(operant);
            arguments.clear();
            operations.add(operation);
        }
    }
}
