package dev.zooty.day8;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Playground {
    private final int connectionLimit;
    private final List<JunctionBox> junctionBoxes;

    private record BoxesAndDistance(JunctionBox boxA, JunctionBox boxB, double distance) {
    }

    public Playground(BufferedReader inputReader, int connectionLimit) {
        this.connectionLimit = connectionLimit;
        junctionBoxes = inputReader.lines()
                .map(this::parseLine)
                .toList();
    }

    public long getThreeLargestCircuits() {
        List<BoxesAndDistance> boxesAndDistances = getBoxesAndDistancesStream(connectionLimit);
        ArrayList<HashSet<JunctionBox>> circuits = getCircuits(boxesAndDistances);
        return circuits
                .stream()
                .sorted((o1, o2) -> o2.size() - o1.size())
                .mapToInt(HashSet::size)
                .limit(3)
                .reduce(1, (left, right) -> left * right);
    }

    public int getLastTwoBoxesValue() {
        List<BoxesAndDistance> boxesAndDistances = getBoxesAndDistances();
        var lastConnection = getLastConnection(boxesAndDistances);
        return (int) (lastConnection.boxA().x() * lastConnection.boxB().x());
    }

    private List<BoxesAndDistance> getBoxesAndDistances() {
        return getBoxesAndDistancesStream().toList();
    }

    private List<BoxesAndDistance> getBoxesAndDistancesStream(int limit) {
        return getBoxesAndDistancesStream()
                .limit(limit)
                .toList();
    }

    private Stream<BoxesAndDistance> getBoxesAndDistancesStream() {
        return IntStream.range(0, junctionBoxes.size())
                .boxed()
                .flatMap(boxAIndex -> IntStream.range(boxAIndex + 1, junctionBoxes.size())
                        .mapToObj(boxBIndex -> new BoxesAndDistance(
                                junctionBoxes.get(boxAIndex),
                                junctionBoxes.get(boxBIndex),
                                junctionBoxes.get(boxAIndex).distanceTo(junctionBoxes.get(boxBIndex)))))
                .sorted(Comparator.comparingDouble(BoxesAndDistance::distance));
    }

    private ArrayList<HashSet<JunctionBox>> getCircuits(List<BoxesAndDistance> boxesAndDistances) {
        var circuits = new ArrayList<HashSet<JunctionBox>>();
        boxesAndDistances.forEach(boxesAndDistance -> addBoxToCircuitIfNeeded(boxesAndDistance.boxA(), boxesAndDistance.boxB(), circuits));
        return circuits;
    }

    private BoxesAndDistance getLastConnection(List<BoxesAndDistance> boxesAndDistances) {
        var circuits = new ArrayList<>(junctionBoxes.stream().map(junctionBox -> new HashSet<>(List.of(junctionBox))).toList());
        for (var boxesAndDistance : boxesAndDistances) {
            addBoxToCircuitIfNeeded(boxesAndDistance.boxA(), boxesAndDistance.boxB(), circuits);
            if (circuits.size() == 1) {
                return boxesAndDistance;
            }
        }
        throw new IllegalStateException("No last connection found");
    }

    private void addBoxToCircuitIfNeeded(JunctionBox boxA, JunctionBox boxB, ArrayList<HashSet<JunctionBox>> circuits) {
        var circuitA = findCircuit(boxA, circuits);
        var circuitB = findCircuit(boxB, circuits);
        if (circuitA.isEmpty() && circuitB.isEmpty()) {
            circuits.add(new HashSet<>(List.of(boxA, boxB)));
        }

        if (circuitA.isPresent() && circuitB.isPresent()
            && circuitA.get() != circuitB.get()) {
            circuitA.get().addAll(circuitB.get());
            circuits.remove(circuitB.get());
        }

        if (circuitA.isEmpty() && circuitB.isPresent()) {
            circuitB.get().add(boxA);
        }

        if (circuitB.isEmpty() && circuitA.isPresent()) {
            circuitA.get().add(boxB);
        }
    }

    private Optional<HashSet<JunctionBox>> findCircuit(JunctionBox junctionBox, List<HashSet<JunctionBox>> circuits) {
        return circuits.parallelStream()
                .filter(circuit -> circuit.contains(junctionBox))
                .findAny();
    }

    private JunctionBox parseLine(String line) {
        var split = line.split(",");
        return new JunctionBox(Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
    }
}
