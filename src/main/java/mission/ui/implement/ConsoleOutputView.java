package mission.ui.implement;

import mission.ui.OutputView;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printSurvivalResult(int days) {
        System.out.printf("%d일간 생존했습니다. %n", days);
    }

    @Override
    public void printError(Exception e) {
        System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
    }

}
