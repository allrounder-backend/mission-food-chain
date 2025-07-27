package mission.exception;

public class InvalidInputException extends RuntimeException{
    InputErrorCode inputErrorCode;

    public InvalidInputException(InputErrorCode inputErrorCode) {
        this.inputErrorCode = inputErrorCode;
    }

    public InputErrorCode getErrorCode() {
        return inputErrorCode;
    }
}
