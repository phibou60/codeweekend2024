package martin.exo1.domain;

import java.util.HashMap;
import java.util.Map;

public final class LevelUtils {

    private static final Map<Integer, Integer> REQUIREMENTS = new HashMap<>();

    private LevelUtils() {}

    public static int getExpRequiredForLevelUp(int level) {
        if (level == 0) {
            return 0;
        }
        if (REQUIREMENTS.containsKey(level)) {
            return REQUIREMENTS.get(level);
        }
        int requirement = getExpRequiredForLevelUp(level - 1) + 1000 + (level) * (level - 1) * 50;
        REQUIREMENTS.put(level, requirement);
        return requirement;
    }

}
