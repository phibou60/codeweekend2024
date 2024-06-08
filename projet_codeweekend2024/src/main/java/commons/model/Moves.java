package commons.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/*
{
"moves": [
//    move1,
//    move2,
//    ...,
//    moveK
]
}

 */
public class Moves {

    private List<Action> moves = new ArrayList<>();
    @JsonIgnore
    private int score;

    public List<Action> getMoves() {
        return moves;
    }

    public void setMoves(List<Action> moves) {
        this.moves = moves;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
