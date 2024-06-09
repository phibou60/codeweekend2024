package commons;

import java.util.List;

import commons.model.GameInput;

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

    /**
     * Print the gameState
     */
    public static void printGameInput(GameInput input, int height) {
        double ratio = (double) input.getWidth() / input.getHeight();
        
        int width = (int) Math.round(height * ratio);
        double scale = (double) input.getHeight() / height;
        
        char[][] board = new char[height][width];
        
        char[] chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        
        int x, y;
        
        for (int i = 0; i < input.getMonsters().size(); i++) {
            x = Math.min(width -1, (int) Math.round(input.getMonsters().get(i).getX() / scale));
            y = Math.min(height -1, (int) Math.round(input.getMonsters().get(i).getY() / scale));
            
            board[y][x] = i < chars.length ? chars[i] : '#';
        }
        
        x = Math.min(width -1, (int) Math.round(input.getStartX() / scale));
        y = Math.min(height -1, (int) Math.round(input.getStartY() / scale));
        
        board[y][x] = 'X';
        
        String border = "";
        for (x = 0; x < width; x++) border += "--";
        border = "+" + border + "+";
        System.out.println(border);
        
        for (y = 0; y < height; y++) {
            String line = "";
            for (x = 0; x < width; x++) {
                line += board[y][x] + " ";
            }
            System.out.println("!" + line + "!");
        }
        
        System.out.println(border);
        
    }
}
