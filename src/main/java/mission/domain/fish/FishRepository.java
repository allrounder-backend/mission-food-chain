package mission.domain.fish;

import java.util.*;
import java.util.stream.Collectors;

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

    public Optional<Fish> feed(List<FishType> ediblePreys) {
        return ediblePreys.stream()
                .map(type -> fishesByType.getOrDefault(type, new LinkedList<>()))
                .filter(queue -> !queue.isEmpty())
                .findFirst()
                .map(Queue::poll);
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

    public FishRepository deepCopy() {
        FishRepository copy = new FishRepository();
        for (Map.Entry<FishType, Queue<Fish>> entry : this.fishesByType.entrySet()) {
            copy.fishesByType.put(entry.getKey(), new LinkedList<>(entry.getValue()));
        }
        return copy;
    }

    public List<FishType> getLivingPredatorsShuffled() {
        return fishesByType.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .filter(type -> !type.getNutritionLevel().isExcludedFromSurvivalCalculation())
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.shuffle(list);
                    return list;
                }));
    }

    public List<FishType> getLivingPredators() {
        return fishesByType.entrySet().stream()
                .filter(e -> !e.getValue().isEmpty())
                .filter(e -> !e.getKey().getNutritionLevel().isExcludedFromSurvivalCalculation())
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
    }


}
