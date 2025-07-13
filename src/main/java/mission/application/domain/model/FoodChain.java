package mission.application.domain.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import mission.application.domain.dto.FishDto;
import mission.application.domain.dto.PossessingFishDto;

public class FoodChain {
    ArrayList<Fish> fishes;
    int day;

    public FoodChain(List<FishDto> database, List<PossessingFishDto> possessingFishes) {
        day = 0;
        fishes = new ArrayList<>(
                possessingFishes.stream()
                        .map(item -> {
                            FishDto fishInfo = database.stream()
                                    .filter(fish -> Objects.equals(item.fishName(), fish.name()))
                                    .findAny()
                                    .get();
                            return new Fish(fishInfo.id(), fishInfo.name(), fishInfo.trophic(),
                                    fishInfo.preyIds(), item.quantity());
                        })
                        .sorted(Comparator.comparing(Fish::getId))
                        .toList()
        );
    }

    public int calculateSurvivalPeriod() {
        if (fishes.stream().allMatch(item -> Objects.equals(item.getName(), "플랑크톤"))) {
            return day;
        }
        onDayPredation();
        day++;
        return calculateSurvivalPeriod();
    }

    private void onDayPredation() {
        List<Fish> copy = new ArrayList<>(fishes);
        for (Fish predator : copy) {
            if (predator.getTrophic() == 1)
                continue;
            onSpicePredation(predator, fishes);
        }
    }

    private void onSpicePredation(Fish predator, List<Fish> fishes) {
        int needed = predator.getQuantity();
        int eaten = 0;

        for (Iterator<Fish> iterator = fishes.iterator(); iterator.hasNext() && eaten < needed; ) {
            Fish prey = iterator.next();

            if (prey == predator || !predator.getPreyIds().contains(prey.getId()))
                continue;

            while (prey.getQuantity() > 0 && eaten < needed) {
                prey.subQuantity();
                eaten++;
            }

            if (prey.getQuantity() == 0) {
                iterator.remove();
            }
        }

        int starved = needed - eaten;
        predator.subQuantity(starved);

        if (predator.getQuantity() <= 0) {
            fishes.remove(predator);
        }
    }
}
