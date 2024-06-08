package martin.exo1.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttributeTest {

    @Test
    void should_compute_value_according_to_level() {
        Attribute attribute = new Attribute(10, 50);

        assertEquals(10, attribute.getValue(0));
        assertEquals(15, attribute.getValue(1));
        assertEquals(20, attribute.getValue(2));
        assertEquals(25, attribute.getValue(3));
        assertEquals(30, attribute.getValue(4));
        assertEquals(35, attribute.getValue(5));
    }
}
