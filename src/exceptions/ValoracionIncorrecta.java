package exceptions;

public class ValoracionIncorrecta extends Exception {
    public ValoracionIncorrecta() {
        super("La valoración debe estar entre 0 y 10.");
    }
}
