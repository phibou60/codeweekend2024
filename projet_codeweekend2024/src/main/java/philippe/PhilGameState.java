package philippe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commons.model.Action;
import commons.model.Attack;
import commons.model.GameInput;
import commons.model.Move;

public class PhilGameState {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final int[] XP_LEVELS = new int[100];
    static {
        XP_LEVELS[0] = 1_000; XP_LEVELS[1] = 1_100; XP_LEVELS[2] = 1_300; XP_LEVELS[3] = 1_600; ; XP_LEVELS[4] = 2_000;
        int prev = 2_000;
        for (int i = 5; i < 100; i++) {
            int L = i + 1;
            XP_LEVELS[i] = 1000 + L * (L - 1) * 50;
            LOGGER.trace("XP_LEVELS[{}] = {}", L, XP_LEVELS[i]);
        }
    }
    
    GameInput gameInput;
    int turn;
    
    int x;
    int y;

    int speed;
    int power;
    int range;
    int[] hps;

    int level;
    int gold;
    int xp;
    
    public PhilGameState(GameInput gameInput) {
        this.gameInput = gameInput;
        x = gameInput.getStartX();
        y = gameInput.getStartY();
        speed = gameInput.getHero().getBaseSpeed();
        power = gameInput.getHero().getBasePower();
        range = gameInput.getHero().getBaseRange();
        
        hps = new int[gameInput.getMonsters().size()];
        for (int i = 0; i < hps.length; i++) hps[i] = gameInput.getMonsters().get(i).getHp();
        
        LOGGER.debug("Init: gold: {}, xp: {}", gold, xp);
    }

    public void play(Action action) {
        if (action instanceof Move) {
            playMove((Move) action);
        } else {
            playAttack((Attack) action);
        }
    }

    private void playMove(Move move) {
        // Check distance
        
        if (move.getTargetX() < 0 || move.getTargetX() > gameInput.getWidth()
                || move.getTargetY() < 0 || move.getTargetY() > gameInput.getHeight()) {
            throw new IllegalArgumentException("Move out of the board: " + move);
        }
        
        double dist = Math.hypot(x - move.getTargetX(), y - move.getTargetY());
        LOGGER.debug("playMove : {}, dist: {} / {}", move, dist, speed);
        
        if (dist > speed) {
            throw new IllegalArgumentException("Move invalide: " + move + ", dist: " + dist + ", speed: " + speed);
        }
        
        x = move.getTargetX();
        y = move.getTargetY();
    }

    private void playAttack(Attack attack) {
        // Check distance
        
        int i = attack.getTargetId();
        
        if (hps[i] == 0) {
            throw new IllegalArgumentException("Already dead: " + attack);
        }
        
        int monsterX = gameInput.getMonsters().get(i).getX();
        int monsterY = gameInput.getMonsters().get(i).getY();
        
        double dist = Math.hypot(x - monsterX, y - monsterY);
        LOGGER.debug("playAttack : {}, dist: {} / {}", attack, dist, range);
        
        if (dist > range) {
            throw new IllegalArgumentException("Attack invalide: " + attack + ", dist: " + dist + ", range: " + range);
        }
        
        hps[i] -= power;
        if (hps[i] <= 0) {
            hps[i] = 0;
            gold += gameInput.getMonsters().get(i).getGold();
            xp += gameInput.getMonsters().get(i).getExp();
            
            LOGGER.debug(" > killed! hp : {}, gold: {}, xp: {}", hps[i], gold, xp);
            if (level != getLevel(xp)) {
                LOGGER.debug(" > level up: level : {}, speed: {}, range: {}, power: {}", level, speed, range, power);
            }
        }

    }
    
    public Coords getPointVers(Coords point2, double speed) {
        LOGGER.trace("getPointVers: de: {}, vers: {}, speed: {}", x + ", " + y, point2, speed);
        double dist = distance(point2);
        if (dist <= speed) {
            return point2;
        }
        double vector_x = point2.x - x;
        double vector_y = point2.y - y;
        double ratio = speed / dist;
        LOGGER.trace(" > dist: {}, vector_x: {}, vector_y: {}, ratio: {}", dist, vector_x, vector_y, ratio);
        
        double newVector_x = (int) (vector_x * ratio);
        double newVector_y = (int) (vector_y * ratio);
        LOGGER.trace(" > newVector_x: {}, newVector_y: {}", newVector_x, newVector_y);
        
        return new Coords(x + newVector_x, y + newVector_y);
    }

    public double distance(Coords c2) {
        return Math.hypot(x - c2.x, y - c2.y);
    }

    public static int getLevel(int xp) {
        int level = 0;
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += XP_LEVELS[i];
            if (xp < sum) {
                level = i;
                break;
            }
        }
        return level;
    }
    
}
