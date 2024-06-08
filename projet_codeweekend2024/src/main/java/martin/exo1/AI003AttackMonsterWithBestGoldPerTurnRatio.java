package martin.exo1;

import commons.model.*;
import martin.AI;
import martin.exo1.domain.Hero;
import martin.exo1.domain.Monster;
import martin.exo1.domain.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AI003AttackMonsterWithBestGoldPerTurnRatio implements AI {

    private final int timeLimit;

    private final Map<Monster, Double> monsterValues = new HashMap<>();

    public AI003AttackMonsterWithBestGoldPerTurnRatio(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public Moves getBestResponse(GameInput input) {
        GameState state = Mapper.mapToGameState(input);
        Queue<GameState> states = new PriorityQueue<>(Comparator.comparingInt(GameState::getEllapsedTime));
        states.add(state);
        GameState bestOverallState = state;

        while (!states.isEmpty()) {
            GameState current = states.poll();
            Map<Monster, Double> monstersValues = current.getMonsters().stream()
                    .collect(Collectors.toMap(Function.identity(), e -> evaluate(e, current.getHero())));
            if (monstersValues.isEmpty()) {
                continue;
            }
            Monster monster = monstersValues.entrySet().stream()
                    .max(Comparator.comparingDouble(Map.Entry::getValue))
                    .get().getKey();
            GameState newState = goKillMonster(current, monster);
            if (newState == null) {
                current.getMonsters().remove(monster);
                states.add(current);
                continue;
            }

            states.add(newState);
            if (newState.getScore() > bestOverallState.getScore()) {
                bestOverallState = newState;
            }
        }

        return Mapper.mapToMoves(bestOverallState);
    }

    private double evaluate(Monster monster, Hero hero) {
        double distance = hero.getPosition().distanceTo(monster.position());
        int turnToReach = (int) Math.ceil((distance - hero.getRange()) / hero.getSpeed());
        int turnToKill = (int) Math.ceil(1.0 * monster.hp() / hero.getPower());
        int score = monster.gold();
        return 1.0 * score / (turnToKill + turnToReach);
    }

    private String getKey(GameState state) {
        return state.getMonsters().stream()
                .map(Monster::id)
                .sorted()
                .map(e -> e.toString())
                .collect(Collectors.joining("|"));
    }

    private GameState goKillMonster(GameState state, Monster monster) {
        var actions = new ArrayList<Action>();
        Hero hero = state.getHero();

        Position heroPosition = hero.getPosition();
        while (!heroPosition.isInRange(monster.position(), hero.getRange())) {
            heroPosition = heroPosition.moveToward(monster.position(), hero.getSpeed());
            actions.add(new Move(heroPosition.x(), heroPosition.y()));
        }
        if (actions.size() + state.getEllapsedTime() > timeLimit) {
            return null;
        }

        int monsterHp = monster.hp();
        while (monsterHp > 0) {
            monsterHp -= hero.getPower();
            actions.add(new Attack(monster.id()));
        }
        if (actions.size() + state.getEllapsedTime() > timeLimit) {
            return null;
        }

        var newHero = new Hero(hero, heroPosition, monster);
        var newMonsters = state.getMonsters().stream()
                .filter(e -> e != monster)
                .toList();
        var newActions = Stream.concat(state.getActions().stream(), actions.stream())
                .toList();
        return new GameState(newHero, newMonsters, newActions);
    }
}
