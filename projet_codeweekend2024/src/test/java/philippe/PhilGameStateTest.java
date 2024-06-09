package philippe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GameState;
import commons.model.Attack;
import commons.model.GameInput;
import commons.model.Move;

class PhilGameStateTest {

    @Test
    void test_play() throws JsonMappingException, JsonProcessingException {
        String json = """
        {
        "hero": {
            "base_speed": 7,
            "base_power": 50,
            "base_range": 7,
            "level_speed_coeff": 25,
            "level_power_coeff": 25,
            "level_range_coeff": 25
        },
        "start_x": 18,
        "start_y": 16,
        "width": 50,
        "height": 50,
        "num_turns": 12,
        "monsters": [ {
                "x": 3,
                "y": 30,
                "hp": 37,
                "gold": 158,
                "exp": 510
            },{
                "x": 12,
                "y": 50,
                "hp": 53,
                "gold": 177,
                "exp": 680
            },{
                "x": 48,
                "y": 22,
                "hp": 56,
                "gold": 194,
                "exp": 540
            },{
                "x": 6,
                "y": 26,
                "hp": 45,
                "gold": 171,
                "exp": 380
            },{
                "x": 46,
                "y": 42,
                "hp": 105,
                "gold": 371,
                "exp": 180
            },{
                "x": 21,
                "y": 20,
                "hp": 18,
                "gold": 141,
                "exp": 230
            }]
        }
        """;
        
        GameInput input = new ObjectMapper().readValue(json, GameInput.class);
        GameState.printGameInput(input, 20);
        
        PhilGameState gameState = new PhilGameState(input);
        gameState.play(new Move(15, 18));
        gameState.play(new Attack(5));
    }


    @Test
    void test_getLevel() {
        assertEquals(0, PhilGameState.getLevel(0));
        assertEquals(0, PhilGameState.getLevel(900));
        assertEquals(1, PhilGameState.getLevel(1_000));
        assertEquals(1, PhilGameState.getLevel(2_000));
        assertEquals(2, PhilGameState.getLevel(2_100));
        assertEquals(2, PhilGameState.getLevel(2_500));
        assertEquals(3, PhilGameState.getLevel(3_400));
        assertEquals(3, PhilGameState.getLevel(3_500));
    }
    
}
