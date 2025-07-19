package mission.controller;

import mission.model.Fish;
import mission.util.FishInputParser;
import mission.view.FishInputView;

import java.util.List;
import java.util.Map;

public class FishController {
    private final List<Fish> fishData;
    private final FishInputView inputView;


    public FishController(List<Fish> fishData, FishInputView inputView) {
        this.fishData = fishData;
        this.inputView = inputView;
    }

    public void run(){
        String input = inputView.inputFish();

        Map<String, Integer> fishCounts = FishInputParser.parseInput(input);

        for(Map.Entry<String, Integer> entry : fishCounts.entrySet()){
            String name = entry.getKey();
            int count = entry.getValue();

            Fish fish = findFishByName(name);

        }

    }

    private Fish findFishByName(String name){
        for(Fish fish : fishData){
            if(fish.getName().equals(name)){
                return fish;
            }
        }
        throw new RuntimeException("존재하지 않는 물고기입니다.");
    }
}
