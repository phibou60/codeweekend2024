package commons.model;

/*
{
"x": <monster X>,
2
"y": <monster Y>,
"hp": <monster hp>,
"exp": <experience gain for killing this monster>,
"gold": <gold gain for killing this monster>
}
 */
public class Monster {

    private int x;
    private int y;
    private int hp;
    private int exp;
    private int gold;
    private int range;
    private int attack;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
