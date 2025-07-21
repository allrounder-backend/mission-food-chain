package mission.domain.fish.validate;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import mission.domain.fish.FishType;
import mission.domain.fish.exception.DuplicateFishException;
import mission.domain.fish.exception.FishError;
import mission.domain.fish.exception.InvalidFishException;
import mission.domain.fish.utils.FishTypeMapper;

public class FishValidator {

    public static FishType mapAndValidate(String name) {
        return FishTypeMapper.map(name)
                .orElseThrow(() -> new InvalidFishException(FishError.INVALID_FISH_NAME));
    }

    public static void validateNoDuplicate(Map<String, Integer> nameToCount) {
        Set<String> names = new HashSet<>();
        for (String name : nameToCount.keySet()) {
            if (!names.add(name)) {
                throw new DuplicateFishException(FishError.DUPLICATE_FISH_TYPE);
            }
        }
    }

}
