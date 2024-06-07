package commons;

import java.util.List;

/**
 * Classe des données en mémoire
 */
public class GameState {

    public List<String> lines;
    
    /**
     * Parsing des données en entrée.
     */
    public static GameState parse(List<String> lines) {
        GameState gameState = new GameState();
        gameState.lines = lines;
        return gameState;
    }

}
