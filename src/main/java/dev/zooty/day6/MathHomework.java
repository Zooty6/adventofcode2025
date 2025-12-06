package dev.zooty.day6;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MathHomework {
    private final List<Operation> saneOperations = new ArrayList<>();
    private final List<Operation> stupidHentaiOperations = new ArrayList<>();

    @SneakyThrows
    public MathHomework(BufferedReader inputReader1, BufferedReader inputReader2) {
        parseSane(inputReader1);
        parseInsane(inputReader2);
    }

    public long doHomework() {
        return doHomework(saneOperations);
    }

    public long doShittyHomework() {
        return doHomework(stupidHentaiOperations);
    }

    private long doHomework(List<Operation> operations) {
        return operations.parallelStream()
                .mapToLong(Operation::doOperation)
                .sum();
    }

    @SneakyThrows
    private void parseInsane(BufferedReader inputReader) {
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
            stupidHentaiOperations.add(operation);
        }
    }

    private void parseSane(BufferedReader inputReader) throws IOException {
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
            if (i >= saneOperations.size()) {
                saneOperations.add(new Operation());
            }
            Operation operation = saneOperations.get(i);
            operation.getArguments().add(Long.parseLong(split[i]));
        }
    }

    private void addOperants(String nextLine) {
        nextLine = nextLine.trim();
        String[] split = nextLine.split("\\s+");
        for (int i = 0; i < split.length; i++) {
            saneOperations.get(i).setOperant(Operant.ofCharacter(split[i].charAt(0)));
        }
    }
}
