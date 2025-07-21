package mission.model;

import java.util.*;

public class Pond {
    private final FoodChainGraph graph;
    private final Map<Fish, Integer> population;

    public Pond(FoodChainGraph graph, List<FishPopulation> initialPopulations) {
        this.graph = graph;
        this.population = new HashMap<>();
        for (FishPopulation fp : initialPopulations) population.put(fp.getFish(), fp.getCount());
    }

    public int simulateSurvivalDays() {
        int day = 0;

        while (true) {
            Map<Fish, Integer> nextPopulation = new HashMap<>(population);  // 다음 날 생존 물고기 수
            boolean anyPredation = false;

            // 플랑크톤을 제외하고 먹이 사슬 순서대로 처리
            List<Fish> sorted = population.keySet().stream()
                    .filter(f -> f.getLevel() > 1)
                    .sorted(Comparator.comparingInt(Fish::getId))
                    .toList();

            // 생존 물고기 수 카운트
            for (Fish predator : sorted) {
                int predatorCount = population.get(predator);
                int survivor = 0;
                List<Fish> preyList = graph.getPrey(predator).stream()
                        .sorted(Comparator.comparingInt(Fish::getId))
                        .toList();
                int remainingPredator = predatorCount;
                Map<Fish, Integer> availablePrey = new HashMap<>();
                for (Fish prey : preyList) {
                    // getOrDefault: 없으면 0 반환
                    int count = population.getOrDefault(prey, 0);
                    if (count > 0) availablePrey.put(prey, count);
                }

                // 포식자가 먹음
                for (Fish prey : preyList) {
                    int preyAvailable = availablePrey.getOrDefault(prey, 0);    // 현재 먹이 수
                    int eatCount = Math.min(remainingPredator, preyAvailable);      // 포식자가 먹을 수 있는 수
                    if (eatCount > 0) {
                        nextPopulation.put(prey, nextPopulation.get(prey) - eatCount);
                        survivor += eatCount;
                        remainingPredator -= eatCount;
                        anyPredation = true;
                    }
                    if (remainingPredator == 0) break;
                }
                // 남은 포식자 생존
                nextPopulation.put(predator, survivor);
            }

            // 플랑크톤은 먹히지 않으므로 그대로 유지
            population.clear();
            for (Map.Entry<Fish, Integer> entry : nextPopulation.entrySet()) {
                if (entry.getValue() > 0) population.put(entry.getKey(), entry.getValue());
            }
            day++;

            // 플랑크톤만 남았으면 종료
            boolean onlyPlanktonLeft = population.keySet().stream().allMatch(f -> f.getLevel() == 1);
            if (onlyPlanktonLeft || !anyPredation) break;
        }
        return day;
    }
}