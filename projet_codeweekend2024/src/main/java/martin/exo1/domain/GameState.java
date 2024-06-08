package martin.exo1.domain;

import commons.model.Action;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private final Hero hero;
    private final List<Monster> monsters;
    private final List<Action> actions;

    public GameState(Hero hero, List<Monster> monsters) {
        this(hero, monsters, new ArrayList<>());
    }

    public GameState(Hero hero, List<Monster> monsters, List<Action> actions) {
        this.hero = hero;
        this.monsters = new ArrayList<>(monsters);
        this.actions = actions;
    }

    public int getScore() {
        return hero.getGold();
    }

    public int getEllapsedTime() {
        return actions.size();
    }

    public Hero getHero() {
        return hero;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public List<Action> getActions() {
        return actions;
    }
}
