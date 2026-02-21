package exceptions;

public class ConciertoInactivoException extends Exception {
    public ConciertoInactivoException() {
        super("El concierto no está activo.");
    }
}
