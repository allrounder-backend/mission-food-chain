package mission;

import mission.controller.FishController;
import mission.factory.FishFactory;
import mission.model.Fish;
import mission.view.FishInputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Fish> data = FishFactory.createDefaultFishList();

        FishInputView inputView = new FishInputView();
        FishController controller = new FishController(data, inputView);
        controller.run();
    }
}
