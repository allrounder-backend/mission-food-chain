package mission.domain.fish.utils;

import mission.domain.fish.FishType;
import mission.domain.fish.validate.FishValidator;

import java.util.HashMap;
import java.util.Map;

public class FishParser {

    public static Map<FishType, Integer> toFishTypeMap(Map<String, Integer> input) {
        FishValidator.validateNoDuplicate(input);
        Map<FishType, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : input.entrySet()) {
            FishType type = FishValidator.mapAndValidate(entry.getKey());
            result.put(type, entry.getValue());
        }
        return result;
    }
}
