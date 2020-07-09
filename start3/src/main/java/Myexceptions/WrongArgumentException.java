package Myexceptions;

public class WrongArgumentException extends IllegalArgumentException {
    private final IllegalArgumentException exception;
    private final int problemValue;

    public WrongArgumentException(String message, int problemValue) {
        this.exception = new IllegalArgumentException(message);
        this.problemValue = problemValue;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", problemValue, exception.getMessage());
    }
}
