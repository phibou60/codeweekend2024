package martin.exo1.domain;

public record Position(int x, int y) {

    public boolean isInRange(Position another, int range) {
        return range * range >= quickDistanceTo(another);
    }

    public Position moveToward(Position target, int speed) {
        if (isInRange(target, speed)) {
            return target;
        }
        double distance = Math.hypot(x - target.x, y - target.y);
        int deltaX = (int) ((target.x - x) * speed / distance);
        int deltaY = (int) ((target.y - y) * speed / distance);
        return new Position(x + deltaX, y + deltaY);
    }

    public int quickDistanceTo(Position another) {
        return (x - another.x) * (x - another.x) + (y - another.y) * (y - another.y);
    }
}
