package martin;

import commons.model.GameInput;
import commons.model.Moves;

public interface AI {

    Moves getBestResponse(GameInput input);
}
