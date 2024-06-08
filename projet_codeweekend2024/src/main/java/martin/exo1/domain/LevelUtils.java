package martin.exo1.domain;

import java.util.HashMap;
import java.util.Map;

public final class LevelUtils {

    private static final Map<Integer, Integer> REQUIREMENTS = initRequirement();

    private LevelUtils() {}

    public static int getExpRequiredForLevelUp(int level) {
        if (REQUIREMENTS.containsKey(level)) {
            return REQUIREMENTS.get(level);
        }
        int requirement = getExpRequiredForLevelUp(level - 1) + 1000 + (level) * (level - 1) * 50;
        REQUIREMENTS.put(level, requirement);
        return requirement;
    }

    // For level 𝐿, an additional 1000 + 𝐿 ⋅ (𝐿 − 1) ⋅ 50 experience points are required after reaching the previous level. Levels can be obtained infinitely.

    private static Map<Integer, Integer> initRequirement() {
        var requirements = new HashMap<Integer, Integer>();
        requirements.put(0, 0);
        requirements.put(1, 1000);
        requirements.put(2, requirements.get(1) + 1100);
        requirements.put(3, requirements.get(2) + 1300);
        requirements.put(4, requirements.get(3) + 1600);
        requirements.put(5, requirements.get(4) + 2000);
        return requirements;
    }

}
