package mission.controller;

import mission.factory.PredationGraphFactory;
import mission.model.Fish;
import mission.model.FishWithCount;
import mission.util.FishInputParser;
import mission.util.FishMapper;
import mission.Service.SurvivalSimulator;
import mission.view.FishInputView;
import mission.view.OutputView;

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

        Map<String, Integer> fishCountMap = FishInputParser.parseInput(input);
        List<FishWithCount> fishWithCounts = FishMapper.mapToFishWithCount(fishData, fishCountMap);

        Map<Integer, List<Integer>> predationGraph = PredationGraphFactory.createDefaultGraph();

        SurvivalSimulator simulator = new SurvivalSimulator(predationGraph);
        int days = simulator.simulate(fishWithCounts);

        OutputView.resultOutput(days);
    }
}
