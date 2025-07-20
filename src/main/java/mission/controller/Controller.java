package mission.controller;

import mission.model.Pool;
import mission.service.Service;
import mission.view.View;

public class Controller {
    private Service service;
    private View view;

    public Controller(Service service, View view){
        this.service = service;
        this.view = view;
    }
    public void play() {
        Pool pool = new Pool();

        // 먹는 순서
        int [] eatingOrder = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        // 식성 배열: 플랑크톤은 제외
        int [][] foodChain =
                {
                        {1},
                        {1},
                        {1},
                        {2, 3, 4},
                        {2, 3, 4},
                        {2, 3, 4},
                        {5, 6, 7},
                        {5, 6, 7},
                        {5, 6, 7},
                        {8, 9, 10},
                        {8, 9, 10}
                };

        // 물고기 종류별 수 입력 및 물고기 비 내림
        service.fishRain(pool,
                service.StringToArray(view.firstInput()));

        // 먹는 방식 설정
        service.setEating(pool, eatingOrder, foodChain);

        // 생존 일자 출력
        view.lastOutput(service.getSurvivalDay(pool));
    }
}
