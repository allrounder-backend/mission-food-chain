package mission.domain.pond;

import java.util.Comparator;
import java.util.Queue;
import mission.domain.fish.Fish;
import mission.domain.fish.FishRepository;
import mission.domain.fish.FishType;
import mission.domain.strategy.RemoveUnfedStrategy;


public class Pond {

    private final FishRepository fishRepository;
    private final RemoveUnfedStrategy removeUnfedStrategy = new RemoveUnfedStrategy();

    public Pond(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    public int simulate() {
        int days = 0;

        while (fishRepository.hasLivingPredators()) {
            simulateOneDay();
            days++;
        }

        return days;
    }

    private void simulateOneDay() {
        fishRepository.getSnapshot().entrySet().stream()
                .filter(entry -> !entry.getKey().getNutritionLevel().isExcludedFromSurvivalCalculation())
                .sorted(Comparator.comparingInt(e -> e.getKey().getId()))
                .forEach(entry -> feedAndFilterHungryFish(entry.getKey(), entry.getValue()));
    }

    private void feedAndFilterHungryFish(FishType predatorType, int count) {
        long fedCount =
                java.util.stream.IntStream.range(0, count)
                        .filter(i -> fishRepository.feed(predatorType).isPresent())
                        .count();

        Queue<Fish> predatorQueue = fishRepository.getPredatorQueue(predatorType);
        removeUnfedStrategy.removeUnfed(predatorQueue, predatorType, (int) fedCount);
    }

}
