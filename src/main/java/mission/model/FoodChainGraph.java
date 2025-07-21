package mission.model;

import java.util.*;

public class FoodChainGraph {
    // 물고기 : 먹이 목록
    private final Map<Fish, List<Fish>> preyMap = new HashMap<>();

    public void addFish(Fish fish) {
        preyMap.put(fish, new ArrayList<>());
    }

    public void addPreyRelation(Fish predator, Fish prey) {
        preyMap.get(predator).add(prey);
    }

    public List<Fish> getPrey(Fish predator) {
        return preyMap.getOrDefault(predator, Collections.emptyList());
    }
}