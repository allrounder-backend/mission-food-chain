package mission.domain.fish;

import java.util.List;
import java.util.stream.Collectors;

public class FeedingRelation {
    private static final List<Relation> RELATIONS = List.of(
            new Relation(1, FishType.ANCHOVY, FishType.PLANKTON),
            new Relation(2, FishType.SARDINE, FishType.PLANKTON),
            new Relation(3, FishType.SMELT, FishType.PLANKTON),
            new Relation(4, FishType.MACKEREL, FishType.ANCHOVY),
            new Relation(5, FishType.MACKEREL, FishType.SARDINE),
            new Relation(6, FishType.HORSE_MACKEREL, FishType.SARDINE),
            new Relation(7, FishType.SAURY, FishType.ANCHOVY),
            new Relation(8, FishType.SAURY, FishType.SMELT),
            new Relation(9, FishType.SPANISH_MACKEREL, FishType.MACKEREL),
            new Relation(10, FishType.SPANISH_MACKEREL, FishType.HORSE_MACKEREL),
            new Relation(11, FishType.TUNA, FishType.MACKEREL),
            new Relation(12, FishType.TUNA, FishType.HORSE_MACKEREL),
            new Relation(13, FishType.TUNA, FishType.SAURY),
            new Relation(14, FishType.AMBERJACK, FishType.HORSE_MACKEREL),
            new Relation(15, FishType.AMBERJACK, FishType.SAURY),
            new Relation(16, FishType.SWORDFISH, FishType.SPANISH_MACKEREL),
            new Relation(17, FishType.SWORDFISH, FishType.AMBERJACK),
            new Relation(18, FishType.SHARK, FishType.TUNA),
            new Relation(19, FishType.SHARK, FishType.AMBERJACK)
    );

    public static List<FishType> getEdiblePrey(FishType predator) {
        return RELATIONS.stream()
                .filter(r -> r.predator().equals(predator))
                .sorted()
                .map(Relation::prey)
                .collect(Collectors.toList());
    }

    private record Relation(int id, FishType predator, FishType prey) implements Comparable<Relation> {
        @Override
        public int compareTo(Relation o) {
            return Integer.compare(this.id, o.id);
        }
    }
}
