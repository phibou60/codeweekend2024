package martin;

import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import commons.model.Attack;
import commons.model.GameInput;
import commons.model.Move;
import commons.model.Moves;

public class Exo1 {
    private static final String EXO = "exo1";
    private static final String DEV = "martin";

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        new Exo1().run();
    }
    
    public void run() throws Exception {

        for (int i = 1; i < 2; i++) {
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

    Moves getBestResponse(GameInput input) {
        Moves response = new Moves();
        response.getMoves().add(new Move(60, 50));
        response.getMoves().add(new Attack(0));
        response.setScore(10);
        return response;
    }
    
}
