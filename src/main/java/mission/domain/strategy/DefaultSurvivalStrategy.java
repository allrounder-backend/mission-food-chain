package mission.domain.strategy;

import java.util.Collections;
import mission.domain.fish.FishType;
import mission.domain.fish.FishRepository;
import mission.domain.fish.Fish;

import java.util.Comparator;
import java.util.Map;
import java.util.Queue;

public class DefaultSurvivalStrategy implements SurvivalStrategy {
    private final RemoveUnfedStrategy removeUnfedStrategy = new RemoveUnfedStrategy();

    @Override
    public void removeUnfed(Queue<Fish> predatorQueue, FishType predatorType, int fedCount) {

    }

    @Override
    public int simulate(FishRepository repository) {
        int days = 0;
        while (repository.hasLivingPredators()) {
            simulateOneDay(repository);
            days++;
        }
        return days;
    }

    private void simulateOneDay(FishRepository repository) {
        for (Map.Entry<FishType, Integer> entry : repository.getSnapshot().entrySet().stream()
                .filter(e -> !e.getKey().getNutritionLevel().isExcludedFromSurvivalCalculation())
                .sorted(Comparator.comparingInt(e -> e.getKey().getId()))
                .toList()) {

            feedAndFilterHungryFish(repository, entry.getKey(), entry.getValue());
        }
    }

    private void feedAndFilterHungryFish(FishRepository repository, FishType predatorType, int count) {
        long fedCount = java.util.stream.IntStream.range(0, count)
                .filter(i -> repository.feed(Collections.singletonList(predatorType)).isPresent())
                .count();

        Queue<Fish> predatorQueue = repository.getPredatorQueue(predatorType);
        removeUnfedStrategy.removeUnfed(predatorQueue, predatorType, (int) fedCount);
    }
}
