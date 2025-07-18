package mission.domain;

import java.util.List;

public class FishList {
    public static final List<Fish> fishes = List.of(
            new Fish(1, "플랑크톤", 1),
            new Fish(2, "멸치", 2),
            new Fish(3, "정어리", 2),
            new Fish(4, "빙어", 2),
            new Fish(5, "고등어", 3),
            new Fish(6, "전갱이", 3),
            new Fish(7, "꽁치", 3),
            new Fish(8, "삼치", 4),
            new Fish(9, "참치", 4),
            new Fish(10, "방어", 4),
            new Fish(11, "황새치", 5),
            new Fish(12, "상어", 5)
    );

    public static List<Fish> findByIds(List<Integer> Ids) {
        return fishes.stream()
                .filter(fish -> Ids.contains(fish.getId()))
                .toList();
    }
}
