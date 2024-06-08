package martin.exo1.domain;

public record Position(int x, int y) {

    public boolean isInRange(Position another, int range) {
        return range * range >= quickDistanceTo(another);
    }

    public Position moveToward(Position target, int speed) {
        if (isInRange(target, speed)) {
            return target;
        }
        int distance = (int) Math.sqrt(quickDistanceTo(target));
        int deltaX = ((target.x - x) * speed) / distance;
        int deltaY = ((target.y - y) * speed) / distance;
        return new Position(x + deltaX, y + deltaY);
    }

    public int quickDistanceTo(Position another) {
        return (x - another.x) * (x - another.x) + (y - another.y) * (y - another.y);
    }
}
