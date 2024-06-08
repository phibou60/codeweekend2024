package martin.exo1.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelUtilsTest {

    @Test
    void should_compute_exp_required_to_level_up() {
        assertEquals(0, LevelUtils.getExpRequiredForLevelUp(0));
        assertEquals(1000, LevelUtils.getExpRequiredForLevelUp(1));
        assertEquals(2100, LevelUtils.getExpRequiredForLevelUp(2));
        assertEquals(3400, LevelUtils.getExpRequiredForLevelUp(3));
        assertEquals(5000, LevelUtils.getExpRequiredForLevelUp(4));
        assertEquals(7000, LevelUtils.getExpRequiredForLevelUp(5));
        assertEquals(9500, LevelUtils.getExpRequiredForLevelUp(6));
    }
}
