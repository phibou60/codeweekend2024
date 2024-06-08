package martin.exo1;

import commons.model.*;
import martin.AI;
import martin.exo1.domain.*;
import martin.exo1.domain.Hero;
import martin.exo1.domain.Monster;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;

public class AI001BasicBFS implements AI {

    private final int timeLimit;

    public AI001BasicBFS(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public Moves getBestResponse(GameInput input) {
        GameState state = Mapper.mapToGameState(input);
        Queue<GameState> states = new PriorityQueue<>(Comparator.comparingInt(GameState::getEllapsedTime));
        states.add(state);
        GameState bestState = state;

        while (!states.isEmpty()) {
            GameState current = states.poll();
            for (Monster monster : current.getMonsters()) {
                GameState newState = goKillMonster(current, monster);
                if (newState != null) {
                    states.add(newState);
                    if (newState.getScore() > bestState.getScore()) {
                        bestState = newState;
                    }
                }
            }
        }

        return Mapper.mapToMoves(bestState);
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
