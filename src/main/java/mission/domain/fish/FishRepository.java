package mission.domain.fish;

import java.util.*;

public class FishRepository {

    // 각 물고기 타입별로 몇 마리씩 있는지 저장
    private final Map<FishType, Queue<Fish>> fishesByType = new HashMap<>();

    // 특정 타입의 물고기를 count만큼 추가
    public void add(FishType type, int count) {
        fishesByType.putIfAbsent(type, new LinkedList<>());
        Queue<Fish> queue = fishesByType.get(type);
        for (int i = 0; i < count; i++) {
            queue.offer(new Fish(type));
        }
    }

    public int count(FishType type) {
        return fishesByType.getOrDefault(type, new LinkedList<>()).size();
    }

    // feed: 먹이사슬 기준으로 가장 id가 낮은 먹잇감을 찾아 하나 꺼냄
    public Optional<Fish> feed(FishType predatorType) {
        NutritionLevel predatorLevel = predatorType.getNutritionLevel();

        return fishesByType.entrySet().stream()
                .filter(entry -> entry.getKey().getNutritionLevel().getLevel() == predatorLevel.getLevel() - 1)
                .sorted(Comparator.comparingInt(e -> e.getKey().getId()))
                .filter(entry -> !entry.getValue().isEmpty())
                .findFirst()
                .map(entry -> entry.getValue().poll());
    }

    public Queue<Fish> getPredatorQueue(FishType predatorType) {
        return fishesByType.getOrDefault(predatorType, new LinkedList<>());
    }

    // hasLivingPredators: 아직 생존한 계산대상 물고기 존재 여부 확인
    public boolean hasLivingPredators() {
        return fishesByType.entrySet().stream()
                .anyMatch(entry ->
                        !entry.getValue().isEmpty() &&
                                !entry.getKey().getNutritionLevel().isExcludedFromSurvivalCalculation()
                );
    }

    // getSnapshot: 디버깅용 남은 물고기 상태 출력
    public Map<FishType, Integer> getSnapshot() {
        Map<FishType, Integer> result = new TreeMap<>(Comparator.comparingInt(FishType::getId));
        for (Map.Entry<FishType, Queue<Fish>> entry : fishesByType.entrySet()) {
            result.put(entry.getKey(), entry.getValue().size());
        }
        return result;
    }
}
