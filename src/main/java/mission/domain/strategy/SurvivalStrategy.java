package mission.domain.strategy;

import java.util.Queue;
import mission.domain.fish.Fish;
import mission.domain.fish.FishRepository;
import mission.domain.fish.FishType;

public interface SurvivalStrategy {
    void removeUnfed(Queue<Fish> predatorQueue, FishType predatorType, int fedCount);
    int simulate(FishRepository repository);
}
