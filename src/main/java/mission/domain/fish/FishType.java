package mission.domain.fish;

public enum FishType {
    PLANKTON(1, "플랑크톤", NutritionLevel.PLANKTON),
    ANCHOVY(2, "멸치", NutritionLevel.SMALL),
    SARDINE(3, "정어리", NutritionLevel.SMALL),
    SMELT(4, "빙어", NutritionLevel.SMALL),
    MACKEREL(5, "고등어", NutritionLevel.MEDIUM),
    HORSE_MACKEREL(6, "전갱이", NutritionLevel.MEDIUM),
    SAURY(7, "꽁치", NutritionLevel.MEDIUM),
    SPANISH_MACKEREL(8, "삼치", NutritionLevel.LARGE),
    TUNA(9, "참치", NutritionLevel.LARGE),
    AMBERJACK(10, "방어", NutritionLevel.LARGE),
    SWORDFISH(11, "황새치", NutritionLevel.TOP),
    SHARK(12, "상어", NutritionLevel.TOP);

    private final int id;
    private final String name;
    private final NutritionLevel nutritionLevel;

    FishType(int id, String name, NutritionLevel nutritionLevel) {
        this.id = id;
        this.name = name;
        this.nutritionLevel = nutritionLevel;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public NutritionLevel getNutritionLevel() {
        return nutritionLevel;
    }

}
