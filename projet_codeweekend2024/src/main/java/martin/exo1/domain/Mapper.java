package martin.exo1.domain;

import commons.model.GameInput;

import java.util.ArrayList;
import java.util.List;

public final class Mapper {

    private Mapper() {}

    public GameState map(GameInput input) {
        Position limit = new Position(input.getWidth(), input.getHeight());
        int timeLimit = input.getNumTurns();
        Hero hero = mapHero(input);
        List<Monster> monsters = mapMonsters(input);
        return new GameState(limit, timeLimit, hero, monsters);
    }

    private Hero mapHero(GameInput input) {
        commons.model.Hero inputHero = input.getHero();
        Attribute speed = new Attribute(inputHero.getBaseSpeed(), inputHero.getLevelSpeedCoeff());
        Attribute power = new Attribute(inputHero.getBasePower(), inputHero.getLevelPowerCoeff());
        Attribute range = new Attribute(inputHero.getBaseRange(), inputHero.getLevelRangeCoeff());
        Hero hero = new Hero(speed, power, range);
        hero.setPosition(new Position(input.getStartX(), input.getStartY()));
        return hero;
    }

    private List<Monster> mapMonsters(GameInput input) {
        List<commons.model.Monster> inputMonsters = input.getMonsters();
        var monsters = new ArrayList<Monster>();
        for (int i = 0; i < inputMonsters.size(); i++) {
            monsters.add(mapMonster(i, inputMonsters.get(i)));
        }
        return monsters;
    }

    private Monster mapMonster(int id, commons.model.Monster inputMonster) {
        return new Monster(id, new Position(inputMonster.getX(), inputMonster.getY()), inputMonster.getHp(), inputMonster.getExp(), inputMonster.getGold());
    }
}
