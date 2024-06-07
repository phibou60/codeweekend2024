package commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
"hero": {
    "base_speed": <base speed of the hero>,
    "base_power": <base power of the hero>,
    "base_range": <base attack range of the hero>,
    "level_speed_coeff": <bonus to hero speed for each level>,
    "level_power_coeff": <bonus to hero power for each level>,
    "level_range_coeff": <bonus to hero attack range for each level>
},
 */
public class Hero {

    @JsonProperty("base_speed")
    private int baseSpeed;
    @JsonProperty("base_power")
    private int basePower;
    @JsonProperty("base_range")
    private int baseRange;
    @JsonProperty("level_speed_coeff")
    private int levelSpeedCoeff;
    @JsonProperty("level_power_coeff")
    private int levelPowerCoeff;
    @JsonProperty("level_range_coeff")
    private int levelRangeCoeff;

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public int getBasePower() {
        return basePower;
    }

    public void setBasePower(int basePower) {
        this.basePower = basePower;
    }

    public int getBaseRange() {
        return baseRange;
    }

    public void setBaseRange(int baseRange) {
        this.baseRange = baseRange;
    }

    public int getLevelSpeedCoeff() {
        return levelSpeedCoeff;
    }

    public void setLevelSpeedCoeff(int levelSpeedCoeff) {
        this.levelSpeedCoeff = levelSpeedCoeff;
    }

    public int getLevelPowerCoeff() {
        return levelPowerCoeff;
    }

    public void setLevelPowerCoeff(int levelPowerCoeff) {
        this.levelPowerCoeff = levelPowerCoeff;
    }

    public int getLevelRangeCoeff() {
        return levelRangeCoeff;
    }

    public void setLevelRangeCoeff(int levelRangeCoeff) {
        this.levelRangeCoeff = levelRangeCoeff;
    }
}
