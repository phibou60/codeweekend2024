package martin.exo1.domain;

import commons.model.GameInput;
import commons.model.Moves;

import java.util.ArrayList;
import java.util.List;

public final class Mapper {

    private Mapper() {}

    public static GameState mapToGameState(GameInput input) {
        Hero hero = mapToHero(input);
        List<Monster> monsters = mapToMonsters(input);
        return new GameState(hero, monsters);
    }

    private static Hero mapToHero(GameInput input) {
        commons.model.Hero inputHero = input.getHero();
        Attribute speed = new Attribute(inputHero.getBaseSpeed(), inputHero.getLevelSpeedCoeff());
        Attribute power = new Attribute(inputHero.getBasePower(), inputHero.getLevelPowerCoeff());
        Attribute range = new Attribute(inputHero.getBaseRange(), inputHero.getLevelRangeCoeff());
        Hero hero = new Hero(speed, power, range);
        hero.setPosition(new Position(input.getStartX(), input.getStartY()));
        return hero;
    }

    private static List<Monster> mapToMonsters(GameInput input) {
        List<commons.model.Monster> inputMonsters = input.getMonsters();
        var monsters = new ArrayList<Monster>();
        for (int i = 0; i < inputMonsters.size(); i++) {
            monsters.add(mapMonster(i, inputMonsters.get(i)));
        }
        return monsters;
    }

    private static Monster mapMonster(int id, commons.model.Monster inputMonster) {
        return new Monster(id, new Position(inputMonster.getX(), inputMonster.getY()), inputMonster.getHp(), inputMonster.getExp(), inputMonster.getGold());
    }

    public static Moves mapToMoves(GameState state) {
        var moves = new Moves();
        moves.getMoves().addAll(state.getActions());
        moves.setScore(state.getScore());
        return moves;
    }
}
