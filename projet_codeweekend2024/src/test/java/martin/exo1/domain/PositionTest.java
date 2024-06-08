package martin.exo1.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PositionTest {

    @Test
    void should_move_partial_distance_to_target() {
        Position start = new Position(0, 0);
        Position target = new Position(20, 30);

        Position result = start.moveToward(target, 10);

        assertEquals(5, result.x());
        assertEquals(8, result.y());
    }

    @Test
    void should_move_to_target_if_within_speed() {
        Position start = new Position(0, 0);
        Position target = new Position(4, 5);

        Position result = start.moveToward(target, 10);

        assertEquals(4, result.x());
        assertEquals(5, result.y());
    }

    @Test
    void should_define_if_target_is_within_range() {
        Position start = new Position(0, 0);
        Position inRangeTarget = new Position(4, 5);
        Position outOfRangeTarget = new Position(6, 9);

        assertTrue(start.isInRange(inRangeTarget, 10));
        assertFalse(start.isInRange(outOfRangeTarget, 10));
    }
}
