package commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
{
"type": "attack",
"target_id": <0-based monster index from the input>
}
 */
public class Attack implements Action {

    @JsonProperty("type")
    private final String type = "attack";
    @JsonProperty("target_id")
    private int targetId;

    public Attack() {
    }

    public Attack(int targetId) {
        this.targetId = targetId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }
}
