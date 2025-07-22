package mission.util;

import mission.exception.ErrorCode;
import mission.exception.InvalidException;
import mission.model.Fish;
import mission.model.FishWithCount;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FishMapper {
    public static List<FishWithCount> mapToFishWithCount(List<Fish> fishData, Map<String, Integer> fishCountMap){
        return fishCountMap.entrySet().stream()
                .map(entry->{
                    Fish fish = fishData.stream()
                            .filter(f-> f.getName().equals(entry.getKey()))
                            .findFirst()
                            .orElseThrow(()-> new InvalidException(ErrorCode.FISH_NOT_FOUND));
                    return new FishWithCount(fish, entry.getValue());
                })
                .collect(Collectors.toList());

    }
}
