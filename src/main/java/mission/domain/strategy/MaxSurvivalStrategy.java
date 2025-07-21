package mission.domain.strategy;

import java.util.Collections;
import java.util.List;
import java.util.Queue;
import mission.domain.fish.Fish;
import mission.domain.fish.FishRepository;
import mission.domain.fish.FishType;
import mission.domain.fish.FeedingRelation;

public class MaxSurvivalStrategy implements SurvivalStrategy {
    private int maxDays = 0;
    private final RemoveUnfedStrategy removeUnfedStrategy = new RemoveUnfedStrategy();

    private static final boolean DEBUG = false;

    @Override
    public void removeUnfed(Queue<Fish> predatorQueue, FishType predatorType, int fedCount) {

    }

    @Override
    public int simulate(FishRepository repository) {
        dfs(repository, 0);
        return maxDays;
    }

    private void dfs(FishRepository repo, int day) {
        if (!repo.hasLivingPredators()) {
            maxDays = Math.max(maxDays, day);
            if (DEBUG) System.out.println("생존 종료 Day " + day);
            return;
        }

        List<FishType> predators = repo.getLivingPredators(); // ← 전체 순열 필요
        permute(predators, 0, repo, day);
    }

    private void permute(List<FishType> predators, int depth, FishRepository original, int day) {
        if (depth == predators.size()) {
            FishRepository copy = original.deepCopy();

            for (FishType predator : predators) {
                int fedCount = 0;
                List<FishType> preyList = FeedingRelation.getEdiblePrey(predator);

                for (int i = 0; i < copy.count(predator); i++) {
                    if (copy.feed(preyList).isPresent()) fedCount++;
                }

                Queue<Fish> predatorQueue = copy.getPredatorQueue(predator);
                removeUnfedStrategy.removeUnfed(predatorQueue, predator, fedCount);
            }

            if (DEBUG) {
                System.out.println("Day " + day + ": " + copy.getSnapshot());
            }

            dfs(copy, day + 1);
            return;
        }

        for (int i = depth; i < predators.size(); i++) {
            Collections.swap(predators, depth, i);
            permute(predators, depth + 1, original, day);
            Collections.swap(predators, depth, i); // backtrack
        }
    }
}
