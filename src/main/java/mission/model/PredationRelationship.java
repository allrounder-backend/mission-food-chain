package mission.model;

import java.util.List;

public enum PredationRelationship {
    ANCHOVY(2, List.of(1)),
    SARDINE(3, List.of(1)),
    ICEFISH(4, List.of(1)),
    MACKEREL(5, List.of(2, 3)),
    HORSE_MACKEREL(6, List.of(3)),
    PACIFIC_SAURY(7, List.of(2, 4)),
    SPANISH_MACKEREL(8, List.of(5, 6)),
    TUNA(9, List.of(5, 6, 7)),
    AMBERJACK(10, List.of(6, 7)),
    SWORDFISH(11, List.of(8, 10)),
    SHARK(12, List.of(9, 10));

    private final int id;
    private final List<Integer> preyIds;

    PredationRelationship(int id, List<Integer> preyIds){
        this.id = id;
        this.preyIds = preyIds;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getPreyIds() {
        return preyIds;
    }
}
