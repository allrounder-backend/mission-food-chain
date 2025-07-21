package mission.controller;

import mission.model.Fish;
import mission.model.FishWithCount;
import mission.util.FishInputParser;
import mission.util.SurvivalSimulator;
import mission.view.FishInputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FishController {
    private final List<Fish> fishData;
    private final FishInputView inputView;


    public FishController(List<Fish> fishData, FishInputView inputView) {
        this.fishData = fishData;
        this.inputView = inputView;
    }

    public void run(){
        String input = inputView.inputFish();

        Map<String, Integer> fishCountMap = FishInputParser.parseInput(input);

        //객체 + 개체수
        List<FishWithCount> fishWithCounts = fishCountMap.entrySet().stream()
                        .map(entry -> {
                            Fish fish = fishData.stream()
                                    .filter(f-> f.getName().equals(entry.getKey()))
                                    .findFirst()
                                    .orElseThrow(()-> new IllegalArgumentException("존재하지 않음"));
                            return new FishWithCount(fish, entry.getValue());
                        })
                                .collect(Collectors.toList());

        Map<Integer, List<Integer>> predationGraph = new HashMap<>();
        predationGraph.put(2, List.of(1));
        predationGraph.put(3, List.of(1));
        predationGraph.put(4, List.of(1));
        predationGraph.put(5, List.of(2, 3));
        predationGraph.put(6, List.of(3));
        predationGraph.put(7, List.of(2, 4));
        predationGraph.put(8, List.of(5, 6));
        predationGraph.put(9, List.of(5, 6, 7));
        predationGraph.put(10, List.of(6, 7));
        predationGraph.put(11, List.of(8, 10));
        predationGraph.put(12, List.of(9, 10));


        SurvivalSimulator simulator = new SurvivalSimulator(predationGraph);
        int days = simulator.simulate(fishWithCounts);

        System.out.println(days + "일간 생존했습니다.");
    }
}
