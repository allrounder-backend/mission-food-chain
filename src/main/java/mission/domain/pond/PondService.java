package mission.domain.pond;

import mission.domain.fish.FishRepository;
import mission.domain.fish.FishType;

import java.util.Map;
import mission.domain.strategy.SurvivalStrategy;

public class PondService {

    private final FishRepository fishRepository;

    public PondService(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    public void initializeFish(Map<FishType, Integer> initialFish) {
        for (Map.Entry<FishType, Integer> entry : initialFish.entrySet()) {
            fishRepository.add(entry.getKey(), entry.getValue());
        }
    }

    public PondResultDto simulate(SurvivalStrategy strategy) {
        Pond pond = new Pond(fishRepository, strategy);
        int survivalDays = pond.simulate();
        return new PondResultDto(survivalDays);
    }

}
