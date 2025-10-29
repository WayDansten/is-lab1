package exception;

public class DifficultyException extends RuntimeException {
    public DifficultyException() {
        super("Difficulty cannot be lowered below minimum");
    }
}
