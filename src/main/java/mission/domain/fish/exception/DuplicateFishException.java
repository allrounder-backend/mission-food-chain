package mission.domain.fish.exception;

public class DuplicateFishException extends IllegalArgumentException {

    private final FishError error;

    public DuplicateFishException(FishError error) {
        super(error.getMessage());
        this.error = error;
    }

    public FishError getError() {
        return error;
    }
}
