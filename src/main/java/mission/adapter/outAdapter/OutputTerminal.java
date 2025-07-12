package mission.adapter.outAdapter;

import mission.application.port.outPort.Logger;

public class OutputTerminal implements Logger {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}