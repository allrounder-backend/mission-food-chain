package mission.domain.pond;

import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import mission.domain.fish.FeedingRelation;
import mission.domain.fish.Fish;
import mission.domain.fish.FishRepository;
import mission.domain.fish.FishType;
import mission.domain.strategy.RemoveUnfedStrategy;
import mission.domain.strategy.SurvivalStrategy;


public class Pond {
    private final FishRepository fishRepository;
    private final SurvivalStrategy strategy;

    public Pond(FishRepository fishRepository, SurvivalStrategy strategy) {
        this.fishRepository = fishRepository;
        this.strategy = strategy;
    }

    public int simulate() {
        return strategy.simulate(fishRepository);
    }
}
