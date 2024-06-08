package martin.exo1.domain;

public class Hero {

    private Position position;
    private final Attribute speed;
    private final Attribute power;
    private final Attribute range;
    private int level;
    private int exp;
    private int gold;

    public Hero(Attribute speed, Attribute power, Attribute range) {
        this.speed = speed;
        this.power = power;
        this.range = range;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSpeed() {
        return speed.getValue(level);
    }

    public int getPower() {
        return power.getValue(level);
    }

    public int getRange() {
        return range.getValue(level);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
