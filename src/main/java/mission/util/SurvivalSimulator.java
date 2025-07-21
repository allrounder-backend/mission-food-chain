package mission.util;

import mission.model.FishWithCount;
import java.util.*;

public class SurvivalSimulator {
    private Map<Integer, List<Integer>> predationGraph;

    public SurvivalSimulator(Map<Integer, List<Integer>> predationGraph) {
        this.predationGraph = predationGraph;
    }

    public int simulate(List<FishWithCount> fishList) {
        Map<Integer, FishWithCount> fishMap = new HashMap<>();

        for (FishWithCount fwc : fishList) {
            fishMap.put(fwc.getFish().getId(), fwc);
        }

        int day = 0;

        while (true) {
            List<Integer> predatorIds = new ArrayList<>(fishMap.keySet());
            Collections.sort(predatorIds); //id오름차순 정렬

            for (int predatorId : predatorIds) {
                FishWithCount predator = fishMap.get(predatorId);

                //플랑크톤 건너뛰기
                if (predator.getFish().getTrophicLevel() == 1)
                    continue;

                // 개체수 0이면 건너뛰기
                if (predator.getCount() == 0)
                    continue;

                List<Integer> preyList = predationGraph.getOrDefault(predatorId, List.of());

                int fedCount = 0;

                //가능한 먹이에 대해
                for (int preyId : preyList) {
                    FishWithCount prey = fishMap.get(preyId);

                    //포식자 개체수만큼 먹기
                    while (predator.getCount() > fedCount && prey != null && prey.getCount() > 0) {
                        prey.eaten(1); //먹이 count-
                        fedCount++; //먹은 수
                    }
                    //개체수와 먹은 수 같으면 다음으로
                    if (predator.getCount() == fedCount)
                        break;
                }

                //먹은것보다 개체수가 많아서 굶어죽는 거 계산
                if (fedCount < predator.getCount()) {
                    int died = predator.getCount() - fedCount;
                    predator.eaten(died);
                }
            }

            //다 먹혔는지 검사
            boolean allNonPlanktonDead = fishMap.values().stream()
                    .filter(fwc -> fwc.getFish().getTrophicLevel() > 1)
                    .allMatch(fwc -> fwc.getCount() == 0);

            if (allNonPlanktonDead)
                break;

            day++;
        }

        return day+1;
    }

}


