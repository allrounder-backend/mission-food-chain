package mission.exception;

public class InvalidException extends RuntimeException{
    ErrorCode errorCode;

    public InvalidException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
