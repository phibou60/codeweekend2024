package martin.exo1.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeroTest {

    @Test
    void should_level_up_when_killing_a_monster_with_enough_exp() {
        Hero hero = new Hero(new Attribute(1, 1), new Attribute(1, 1), new Attribute(1, 1));
        Monster monster = new Monster(0, new Position(5, 5), 10, 1200, 100);

        Hero result = new Hero(hero, new Position(0, 0), monster);

        Assertions.assertEquals(1, result.getLevel());
    }

    @Test
    void should_level_up_multiple_times_when_killing_a_monster_with_enough_exp() {
        Hero hero = new Hero(new Attribute(1, 1), new Attribute(1, 1), new Attribute(1, 1));
        Monster monster = new Monster(0, new Position(5, 5), 10, 8000, 100);

        Hero result = new Hero(hero, new Position(0, 0), monster);

        Assertions.assertEquals(5, result.getLevel());
    }
}
