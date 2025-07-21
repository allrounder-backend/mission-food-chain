package mission.domain.fish;

public class Fish {
    private final FishType type;

    public Fish(FishType type) {
        this.type = type;
    }

    public FishType getType() {
        return type;
    }

    public NutritionLevel getNutritionLevel() {
        return type.getNutritionLevel();
    }

    public boolean canEat(Fish prey) {
        return this.getNutritionLevel().isPrey(prey.getNutritionLevel());
    }

    public boolean isExcludedFromSurvivalCalculation() {
        return type.getNutritionLevel().isExcludedFromSurvivalCalculation();
    }

    public String getName() {
        return type.getName();
    }
}
