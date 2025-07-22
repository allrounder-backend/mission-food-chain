package mission.exception;

public enum ErrorCode {
    INVALID_FORMAT("잘못된 입력 형식입니다."),
    INVALID_COUNT("물고기 수는 1 이상이어야 합니다."),
    FISH_NOT_FOUND("해당 물고기는 존재하지 않습니다."),
    EMPTY_INPUT("입력에 유효한 항목이 없습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
