package mission.controller.implement;

import java.util.Map;
import mission.controller.Controller;
import mission.domain.fish.FishType;
import mission.domain.fish.utils.FishParser;
import mission.domain.pond.PondResultDto;
import mission.domain.pond.PondService;
import mission.domain.strategy.MaxSurvivalStrategy;
import mission.domain.strategy.SurvivalStrategy;
import mission.ui.InputView;
import mission.ui.OutputView;

public class PondController implements Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final PondService pondService;

    public PondController(InputView inputView, OutputView outputView, PondService pondService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pondService = pondService;
    }

    @Override
    public void run() {
        try {
            Map<String, Integer> nameToCount = inputView.inputFishRain();
            Map<FishType, Integer> fishCounts = FishParser.toFishTypeMap(nameToCount);
            pondService.initializeFish(fishCounts);

            SurvivalStrategy strategy = new MaxSurvivalStrategy();
            PondResultDto result = pondService.simulate(strategy);

            outputView.printSurvivalResult(result.survivalDays());

        } catch (Exception e) {
            outputView.printError(e);
        }
    }

}
