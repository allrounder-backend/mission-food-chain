package mission.util;

import mission.model.FishWithCount;
import java.util.*;
import java.util.stream.Collectors;

public class SurvivalSimulator {

    public int simulate(List<FishWithCount> fishList) {
        int day = 0;

        while (true) {
            boolean hasPredator = false;

            // 1. 현재 생존 중인 포식자만 골라냄 (trophicLevel > 1)
            Map<Integer, FishWithCount> pool = fishList.stream()
                    .filter(fwc -> fwc.getFish().getTrophicLevel() > 1 && fwc.getCount() > 0)
                    .collect(Collectors.toMap(
                            fwc -> fwc.getFish().getId(),
                            fwc -> fwc
                    ));

            if (pool.isEmpty())
                break;

            // 2. 포식자를 id 오름차순으로 정렬 (낮은 id가 먼저 먹음)
            List<FishWithCount> sorted = new ArrayList<>(pool.values());
            sorted.sort(Comparator.comparingInt(fwc -> fwc.getFish().getId()));

            // 3. 각 포식자가 하루에 한 마리씩 먹이 사냥
            for (FishWithCount predator : sorted) {
                int predatorLevel = predator.getFish().getTrophicLevel();
                if (predator.getCount() <= 0)
                    continue;

                // 먹이 후보: trophicLevel이 1 낮고 생존 중이며 id 오름차순
                List<FishWithCount> candidates = fishList.stream()
                        .filter(fwc -> fwc.getFish().getTrophicLevel() == predatorLevel - 1 && fwc.getCount() > 0)
                        .sorted(Comparator.comparingInt(fwc -> fwc.getFish().getId()))
                        .collect(Collectors.toList());

                int predatorCount = predator.getCount();
                int fed = 0;

                for (FishWithCount prey : candidates) {
                    int available = Math.min(prey.getCount(), predatorCount - fed);
                    if (available > 0) {
                        prey.eaten(available);
                        fed += available;
                    }
                    if (fed == predatorCount)
                        break;
                }

                // 못 먹은 만큼 죽음
                predator.eaten(predatorCount - fed);

                // 하루라도 생존한 포식자가 있었는지 체크
                if (predator.getCount() > 0)
                    hasPredator = true;
            }

            day++;

            if (!hasPredator)
                break;
        }

        return day;
    }
}
