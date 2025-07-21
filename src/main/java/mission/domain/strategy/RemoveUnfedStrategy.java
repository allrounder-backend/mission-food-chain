package mission.domain.strategy;

import java.util.Queue;
import mission.domain.fish.Fish;
import mission.domain.fish.FishRepository;
import mission.domain.fish.FishType;
import mission.domain.strategy.validate.StrategyValidator;

public class RemoveUnfedStrategy implements SurvivalStrategy {
    public void removeUnfed(Queue<Fish> predatorQueue, FishType predatorType, int fedCount) {
        StrategyValidator.validateFedCount(predatorQueue.size(), fedCount);

        int toRemove = predatorQueue.size() - fedCount;
        for (int i = 0; i < toRemove; i++) {
            predatorQueue.poll();
        }
    }

    @Override
    public int simulate(FishRepository repository) {
        return 0;
    }
}
