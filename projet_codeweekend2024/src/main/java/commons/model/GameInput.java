package commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/*
{
"num_turns": <number of turns>,
"width": <world width>,
"height": <world height>,
"start_x": <hero starting X coordinate>,
"start_y": <hero starting Y coordinate>,
"hero": {
    "base_speed": <base speed of the hero>,
    "base_power": <base power of the hero>,
    "base_range": <base attack range of the hero>,
    "level_speed_coeff": <bonus to hero speed for each level>,
    "level_power_coeff": <bonus to hero power for each level>,
    "level_range_coeff": <bonus to hero attack range for each level>
},
"monsters": [
    <list of monsters>
]
}

 */
public class GameInput {

    @JsonProperty("num_turns")
    private int numTurns;
    private int width;
    private int height;
    @JsonProperty("start_x")
    private int startX;
    @JsonProperty("start_y")
    private int startY;
    private Hero hero;
    private List<Monster> monsters;

    public int getNumTurns() {
        return numTurns;
    }

    public void setNumTurns(int numTurns) {
        this.numTurns = numTurns;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }
}
