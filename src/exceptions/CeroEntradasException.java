package exceptions;

public class CeroEntradasException extends Exception {
    public CeroEntradasException() {
        super("El concierto no tiene entradas vendidas.");
    }
}
