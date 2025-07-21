package mission.domain.fish;

public enum NutritionLevel {
    PLANKTON(1),
    SMALL(2),
    MEDIUM(3),
    LARGE(4),
    TOP(5);

    private final int level;

    NutritionLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public boolean isPrey(NutritionLevel other) {
        return this.level == other.level + 1;
    }

    public boolean isExcludedFromSurvivalCalculation() {
        return this == PLANKTON;
    }
}

