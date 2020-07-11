package by.epam.inner.myexceptions;

public class NegativeArgumentException extends IllegalArgumentException {
    private final int problemValue;
    private final IllegalArgumentException exception;

    public NegativeArgumentException(String message, int problemValue) {
        this.problemValue = problemValue;
        this.exception = new IllegalArgumentException(message);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", problemValue, exception.getMessage());
    }
}
