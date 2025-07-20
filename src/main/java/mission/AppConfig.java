package mission;

import mission.controller.Controller;
import mission.controller.implement.PondController;
import mission.domain.fish.FishRepository;
import mission.domain.pond.PondService;
import mission.ui.InputView;
import mission.ui.OutputView;
import mission.ui.implement.ConsoleInputView;
import mission.ui.implement.ConsoleOutputView;

public class AppConfig {

    public Controller fishController() {
        return new PondController(inputView(), outputView(), pondService());
    }

    private InputView inputView() {
        return new ConsoleInputView();
    }

    private OutputView outputView() {
        return new ConsoleOutputView();
    }

    private PondService pondService() {
        return new PondService(fishRepository());
    }

    private FishRepository fishRepository() {
        return new FishRepository();
    }
}
