package exception;

public class InvalidMoveToUndo extends RuntimeException {
    public InvalidMoveToUndo(String message) {
        super(message);
    }
}
