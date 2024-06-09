package philippe;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GameState;
import commons.model.Action;
import commons.model.Attack;
import commons.model.GameInput;
import commons.model.Monster;
import commons.model.Move;
import commons.model.Moves;

public class Exo1 {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final String EXO = "exo1";
    private static final String DEV = "philippe";
    
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    
    public static void main(String[] args) throws Exception {
        try {
            new Exo1().run();
        } catch (Exception e) {
            LOGGER.throwing(e);
        }
    }
    
    public void run() throws Exception {
        
        int totalScore = 0;
        
        for (int i = 1; i <= 1; i++) {
            URL url = this.getClass().getClassLoader().getResource(String.format("%s/input/%03d.json", EXO, i));

            LOGGER.info("-------------------------------------------------------------");
            LOGGER.info("Test case " + i + ":");

            GameInput input = JSON_MAPPER.readValue(url, GameInput.class);
            if (LOGGER.isDebugEnabled()) GameState.printGameInput(input, 20);
            
            Moves response = getBestResponse(input);

            String submission = JSON_MAPPER.writeValueAsString(response);
            LOGGER.debug("Soumission :\n" + submission);
            LOGGER.info("Score :" + response.getScore());
            totalScore += response.getScore();
        }
        LOGGER.info("Total Score :" + totalScore);
    }

    Moves getBestResponse(GameInput input) {
        Moves response = new Moves();
        List<Action> moves = response.getMoves();
        
        double[][] distances = GetDistanceMatrix.calculate(input);
        TSProblem tsProblem = new TSProblem(distances);
         
        SalesmanGlouton sg = new SalesmanGlouton(tsProblem);
 
        LOGGER.debug("---------Solution SalesmanGlouton:");
        int[] solutionGlouton = sg.getMeilleurSolution();
        LOGGER.debug(Arrays.toString(solutionGlouton));
        LOGGER.debug("distance: " + sg.distSoluce);
        LOGGER.debug("check distance: " + SalesmanTools.getDistance(tsProblem, solutionGlouton));

        System.out.println("---------Amélioration 2 OPT:");
        
        Salesman2Opt s2opt = new Salesman2Opt(tsProblem, solutionGlouton);
        
        int[] solution = s2opt.getMeilleurSolution(System.nanoTime() + 2_000_000L);
        System.out.println(Arrays.toString(solution));
        System.out.println("distance2: " + s2opt.distSoluce);
        System.out.println("nbUpdates: " + s2opt.nbUpdates);
        
        PhilGameState gameState = new PhilGameState(input);
        
        int targetRank = 1;
        int target = solution[targetRank] - 1;
        
        for (int turn = 1; turn <= input.getNumTurns(); turn++) {
            LOGGER.debug("---- TURN {}", turn);
            double dist = getDistanceToTarget(target, input, gameState);
            LOGGER.debug("> target: {}, dist: {}", target, dist);
            Action action = null;
            
            // Si on est assez près on tir
            
            if (dist <= gameState.range) {
                action = new Attack(target);
                gameState.play(action);
                
                LOGGER.debug("> hp ?: {}", gameState.hps[target]);
                
                // Si le monstre est tué, on change de target
                if (gameState.hps[target] <= 0) {
                    LOGGER.debug("Next monster");
                    targetRank++;
                    target = solution[targetRank] - 1;
                    if (target == 0) break; 
                }
            
            // Si on est trop loin, on avance vers lui
                
            } else {
                //Coords coords = gameState.getPointToTarget(target, input, gameState);
                Monster m = input.getMonsters().get(target);
                Coords mCoords = new Coords(m.getX(), m.getY());
                Coords coords = gameState.getPointVers(mCoords, gameState.speed);
                action = new Move((int) Math.floor(coords.x), (int) Math.floor(coords.y));
                gameState.play(action);
            }
            
            moves.add(action);
         }
        
        response.setScore(gameState.gold);
        
        return response;
    }

    private double getDistanceToTarget(int target, GameInput input, PhilGameState gameState) {
        return Math.hypot(gameState.x - input.getMonsters().get(target).getX(), 
                gameState.y - input.getMonsters().get(target).getY());
    }

    private Coords getPointToTarget(int target, GameInput input, PhilGameState gameState) {
        Monster m = input.getMonsters().get(target);
        Coords mCoords = new Coords(m.getX(), m.getY());
        Coords hCoords = new Coords(gameState.x, gameState.y);
        
        return hCoords.getPointVers(mCoords, gameState.speed);
    }
    
}
