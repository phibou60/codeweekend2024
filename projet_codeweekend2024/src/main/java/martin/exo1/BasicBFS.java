package martin.exo1;

import commons.model.Attack;
import commons.model.GameInput;
import commons.model.Move;
import commons.model.Moves;
import martin.AI;

public class BasicBFS implements AI {

    @Override
    public Moves getBestResponse(GameInput input) {
        Moves response = new Moves();
        response.getMoves().add(new Move(60, 50));
        response.getMoves().add(new Attack(0));
        response.setScore(10);
        return response;
    }
}
