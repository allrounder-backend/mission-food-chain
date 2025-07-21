package mission.domain.fish;

import java.util.*;

/**
 * 각 FishType에 대해 먹이 관계를 정의하는 팩토리 클래스
 */
public class FeedingRelation {

    private static final Map<FishType, List<FishType>> RELATION_MAP = new HashMap<>();

    static {
        add(FishType.ANCHOVY, FishType.PLANKTON);
        add(FishType.SARDINE, FishType.PLANKTON);
        add(FishType.SMELT, FishType.PLANKTON);
        add(FishType.MACKEREL, FishType.ANCHOVY, FishType.SARDINE);
        add(FishType.HORSE_MACKEREL, FishType.SARDINE);
        add(FishType.SAURY, FishType.ANCHOVY, FishType.SMELT);
        add(FishType.SPANISH_MACKEREL, FishType.MACKEREL, FishType.HORSE_MACKEREL);
        add(FishType.TUNA, FishType.MACKEREL, FishType.HORSE_MACKEREL, FishType.SAURY);
        add(FishType.AMBERJACK, FishType.HORSE_MACKEREL, FishType.SAURY);
        add(FishType.SWORDFISH, FishType.SPANISH_MACKEREL, FishType.AMBERJACK);
        add(FishType.SHARK, FishType.TUNA, FishType.AMBERJACK);
    }

    private static void add(FishType predator, FishType... prey) {
        RELATION_MAP.put(predator, List.of(prey));
    }


    public static List<FishType> getEdiblePrey(FishType predator) {
        return RELATION_MAP.getOrDefault(predator, List.of());
    }
}
