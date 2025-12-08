package dev.zooty.day8;


public record JunctionBox(double x, double y, double z) {
    public double distanceTo(JunctionBox other) {
        return Math.sqrt(Math.pow(x - other.x(), 2) + Math.pow(y - other.y(), 2) + Math.pow(z - other.z(), 2));
    }
}
