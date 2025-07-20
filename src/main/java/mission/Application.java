package mission;

import api.Console;
import mission.controller.Controller;
import mission.service.Service;
import mission.view.View;

public class Application {
    public static void main(String[] args) {
        //TODO: 미션 구현
        Service service = new Service();
        View view = new View();
        Controller controller = new Controller(service, view);
        controller.play();
    }
}
