package exceptions;

public class ConciertoNoAsistidoException extends Exception {
    public ConciertoNoAsistidoException() {
        super("No has asistido a este concierto.");
    }
}
