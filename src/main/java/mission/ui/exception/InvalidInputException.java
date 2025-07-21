package mission.ui.exception;

public class InvalidInputException extends IllegalArgumentException {
    private final InputError error;

    public InvalidInputException(InputError error) {
        super(error.getMessage());
        this.error = error;
    }

    public InputError getError() {
        return error;
    }
}
