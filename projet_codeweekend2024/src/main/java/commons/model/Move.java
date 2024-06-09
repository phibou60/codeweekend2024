package commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
{
"type": "move",
"target_x": <X>,
"target_y": <Y>
}
 */
public class Move implements Action {

    @JsonProperty("type")
    private final String type = "move";
    @JsonProperty("target_x")
    private int targetX;
    @JsonProperty("target_y")
    private int targetY;

    public Move() {
    }

    public Move(int targetX, int targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    @Override
    public String toString() {
        return "Move [targetX=" + targetX + ", targetY=" + targetY + "]";
    }
    
}
