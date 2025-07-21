package mission.controller;

import mission.model.Fish;
import mission.model.FishWithCount;
import mission.util.FishInputParser;
import mission.util.SurvivalSimulator;
import mission.view.FishInputView;

import java.util.ArrayList;
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


        List<FishWithCount> fishList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : fishCounts.entrySet()){
            int count = entry.getValue();
            Fish fish = findFishByName(entry.getKey());
            fishList.add(new FishWithCount(fish, count));
        }

        SurvivalSimulator simulator = new SurvivalSimulator();
        int days = simulator.simulate(fishList);

        System.out.println(days + "일간 생존했습니다.");

    }

    private Fish findFishByName(String name){
        for(Fish fish : fishData){
            if(fish.getName().equals(name)){
                return fish;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 물고기입니다.");
    }
}
