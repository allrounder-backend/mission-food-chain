package mission.domain.fish.utils;

import java.util.Arrays;
import mission.domain.fish.FishType;

import java.util.Optional;

public class FishTypeMapper {

    public static Optional<FishType> map(String name) {
        return Arrays.stream(FishType.values())
                .filter(fish -> fish.getName().equals(name.trim()))
                .findFirst();
    }
}

