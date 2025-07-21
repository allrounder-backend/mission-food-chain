package mission.ui.exception;

public enum InputError {
    NULL_INPUT("입력이 비어있습니다."),
    INVALID_FORMAT("입력 형식이 올바르지 않습니다. 예시: [정어리-3],[고등어-2]");

    private final String message;

    InputError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

