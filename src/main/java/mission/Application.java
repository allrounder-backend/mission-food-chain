package mission;

import mission.model.Fish;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        //TODO: 미션 구현

        List<Fish> data = List.of(
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


    }
}
