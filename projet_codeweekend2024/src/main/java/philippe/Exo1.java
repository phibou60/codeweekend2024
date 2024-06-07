package philippe;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import commons.GameState;
import commons.Response;

public class Exo1 {
    private static final String EXO = "exo1";
    private static final String DEV = "philippe";
    
    public static void main(String[] args) throws Exception {
        new Exo1().run();
    }
    
    public void run() throws Exception {

        for (int i = 1; i <= 2; i++) {
            URI uri = this.getClass().getClassLoader().getResource(EXO + "/input/" + i + ".txt").toURI();
            List<String> input = Files.readAllLines(Paths.get(uri), Charset.defaultCharset());

            System.out.println("-------------------------------------------------------------");
            System.out.println("Test case " + i + ":");
            System.out.println(input);
            
            GameState gameState = GameState.parse(input);
            Response response = getBestResponse(gameState);

            // TODO : écrire le résultat quelque part sur disque
            
            System.out.println("Soumission :");
            response.getSubmission().forEach(System.out::println);
            
            System.out.println("Score : " + response.getScore());
        }

    }

    Response getBestResponse(GameState gameState) {
        
        List<String> submission = new ArrayList<>();
        submission.add("nb lines: " + gameState.lines.size());
        int score = gameState.lines.size();
        
        return new Response(submission, score);
    }
    
}
