package mission.domain.fish.exception;

public enum FishError {
    INVALID_FISH_NAME("존재하지 않는 생선 이름입니다."),
    DUPLICATE_FISH_TYPE("중복된 물고기 타입이 존재합니다.");

    private final String message;

    FishError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
