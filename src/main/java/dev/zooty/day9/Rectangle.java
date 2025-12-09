package dev.zooty.day9;

public record Rectangle(Coordinate cornerA, Coordinate cornerB) {
    public long area() {
        return (Math.abs(cornerA.x() - cornerB.x()) + 1) * (Math.abs(cornerA.y() - cornerB.y()) + 1);
    }

    public boolean contains(Rectangle other) {
        long left = Math.min(cornerA.x(), cornerB.x());
        long right = Math.max(cornerA.x(), cornerB.x());
        long top = Math.min(cornerA.y(), cornerB.y());
        long bottom = Math.max(cornerA.y(), cornerB.y());
        long leftOther = Math.min(other.cornerA().x(), other.cornerB().x());
        long rightOther = Math.max(other.cornerA().x(), other.cornerB().x());
        long topOther = Math.min(other.cornerA().y(), other.cornerB().y());
        long bottomOther = Math.max(other.cornerA().y(), other.cornerB().y());

        return left < rightOther &&
               right > leftOther &&
               top < bottomOther &&
               bottom > topOther;
    }
}
