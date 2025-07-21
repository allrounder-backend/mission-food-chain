package mission.ui.implement;

import api.Console;
import java.util.Map;
import mission.ui.InputView;
import mission.ui.validate.InputValidator;


public class ConsoleInputView implements InputView {

    @Override
    public Map<String, Integer> inputFishRain() {
        System.out.println("물고기 비로 내릴 물고기를 입력해주세요. (ex. [정어리-5],[고등어-2])");
        String input = Console.readLine();
        return InputValidator.validate(input);
    }
}
