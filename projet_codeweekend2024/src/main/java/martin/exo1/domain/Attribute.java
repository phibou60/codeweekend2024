package martin.exo1.domain;

public record Attribute(int baseValue, int levelCoeff) {

    public int getValue(int level) {
        return baseValue + (baseValue * levelCoeff * level) / 100;
    }
}
