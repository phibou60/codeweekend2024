package martin;

import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import commons.model.GameInput;
import commons.model.Moves;
import martin.exo1.AI001BasicBFS;
import martin.exo1.AI002AttackNearestMonster;

public class Exo1 {
    private static final String EXO = "exo1";
    private static final String DEV = "martin";

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        new Exo1().run();
    }
    
    public void run() throws Exception {

        for (int i = 1; i <= 25; i++) {
            URL url = this.getClass().getClassLoader().getResource(String.format("%s/input/%03d.json", EXO, i));

            System.out.println("-------------------------------------------------------------");
            System.out.println("Test case " + i + ":");

            GameInput input = JSON_MAPPER.readValue(url, GameInput.class);
            Moves response = getBestResponse(input);

            // TODO : écrire le résultat quelque part sur disque
            String submission = JSON_MAPPER.writeValueAsString(response);
            System.out.println("Soumission :\n" + submission);
            System.out.println("Score :" + response.getScore());
        }

    }

    private Moves getBestResponse(GameInput input) {
        AI ai = new AI002AttackNearestMonster(input.getNumTurns());
        return ai.getBestResponse(input);
    }
    
}
