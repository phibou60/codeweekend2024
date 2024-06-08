package martin.exo1.domain;

import java.util.List;

public class GameState {

    private final Position limit;
    private final int timeLimit;
    private Hero hero;
    private final List<Monster> monsters;

    public GameState(Position limit, int timeLimit, Hero hero, List<Monster> monsters) {
        this.limit = limit;
        this.timeLimit = timeLimit;
        this.hero = hero;
        this.monsters = monsters;
    }

    public int getScore() {
        return hero.getGold();
    }

    public Position getLimit() {
        return limit;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }
}
