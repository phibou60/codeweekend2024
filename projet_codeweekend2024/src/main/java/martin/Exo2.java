package martin;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.model.GameInput;
import commons.model.Moves;
import martin.exo1.AI003AttackMonsterWithBestGoldPerTurnRatio;

import java.net.URL;

public class Exo2 {
    private static final String EXO = "exo2";
    private static final String DEV = "martin";

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static void main(String[] args) throws Exception {
        new Exo2().run();
    }
    
    public void run() throws Exception {

        for (int i = 26; i <= 28; i++) {
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
        AI ai = new AI003AttackMonsterWithBestGoldPerTurnRatio(input.getNumTurns());
        return ai.getBestResponse(input);
    }
    
}
