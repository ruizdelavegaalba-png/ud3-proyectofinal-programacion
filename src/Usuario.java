import exceptions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Objects;

public class Usuario {
    // Atributos
    private String nombre;
    private int edad;
    private ArrayList<Entrada> entradasCompradas;
    private HashSet<Concierto> conciertosAsistidos;
    private HashMap<Concierto, Integer> valoraciones;

    // Constructores
    public Usuario(String nombre, int edad, ArrayList<Entrada> entradasCompradas, HashSet<Concierto> conciertosAsistidos, HashMap<Concierto, Integer> valoraciones) {
        this.nombre = nombre;
        this.edad = edad;
        this.entradasCompradas = entradasCompradas;
        this.conciertosAsistidos = conciertosAsistidos;
        this.valoraciones = valoraciones;
    }

    public Usuario() {
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public ArrayList<Entrada> getEntradasCompradas() { return entradasCompradas; }
    public void setEntradasCompradas(ArrayList<Entrada> entradasCompradas) { this.entradasCompradas = entradasCompradas; }

    public HashSet<Concierto> getConciertosAsistidos() { return conciertosAsistidos; }
    public void setConciertosAsistidos(HashSet<Concierto> conciertosAsistidos) { this.conciertosAsistidos = conciertosAsistidos; }

    public HashMap<Concierto, Integer> getValoraciones() { return valoraciones; }
    public void setValoraciones(HashMap<Concierto, Integer> valoraciones) { this.valoraciones = valoraciones; }

    public void comprarEntrada(Concierto concierto, Entrada.TipoEntrada tipo)
            throws ConciertoInactivoException, ConciertoYaAsistidoException, AforoCompletoException {

        if (!concierto.isActivo()) {
            throw new ConciertoInactivoException();
        }
        if (conciertosAsistidos.contains(concierto)) {
            throw new ConciertoYaAsistidoException();
        }
        if (!concierto.entradasDisponibles()) {
            throw new AforoCompletoException();
        }

        Entrada nuevaEntrada = new Entrada(concierto, tipo);
        concierto.getEntradasVendidas().add(nuevaEntrada);
        entradasCompradas.add(nuevaEntrada);
        conciertosAsistidos.add(concierto);

        System.out.println("Entrada comprada exitosamente: " + nuevaEntrada);
    }

    public void valorar(Concierto concierto, int valoracion)
            throws ConciertoNoAsistidoException, ValoracionIncorrecta {

        if (!conciertosAsistidos.contains(concierto)) {
            throw new ConciertoNoAsistidoException();
        }
        if (valoracion < 0 || valoracion > 10) {
            throw new ValoracionIncorrecta();
        }

        valoraciones.put(concierto, valoracion);
        System.out.println("Valoración registrada: " + valoracion + "/10 para " + concierto);
    }

    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return edad == usuario.edad
                && Objects.equals(nombre, usuario.nombre)
                && Objects.equals(entradasCompradas, usuario.entradasCompradas)
                && Objects.equals(conciertosAsistidos, usuario.conciertosAsistidos)
                && Objects.equals(valoraciones, usuario.valoraciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, edad, entradasCompradas, conciertosAsistidos, valoraciones);
    }

    @Override
    public String toString() {
        return this.nombre + " (ha asistido a " + this.conciertosAsistidos.size() + " conciertos)";
    }
}