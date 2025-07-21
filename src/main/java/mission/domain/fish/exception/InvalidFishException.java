package mission.domain.fish.exception;

public class InvalidFishException extends IllegalArgumentException {

    private final FishError error;

    public InvalidFishException(FishError error) {
        super(error.getMessage());
        this.error = error;
    }

    public FishError getError() {
        return error;
    }
}
