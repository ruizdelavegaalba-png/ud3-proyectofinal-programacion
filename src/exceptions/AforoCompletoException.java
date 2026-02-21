package exceptions;

public class AforoCompletoException extends Exception {
    public AforoCompletoException() {
        super("No hay entradas disponibles, el aforo está completo.");
    }
}
