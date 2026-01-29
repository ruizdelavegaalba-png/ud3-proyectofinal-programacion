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
    //Constructor completo y vacio
    public Usuario(String nombre, int edad, ArrayList<Entrada> entradasCompradas, HashSet<Concierto> conciertosAsistidos, HashMap<Concierto, Integer> valoraciones) {
        this.nombre = nombre;
        this.edad = edad;
        this.entradasCompradas = entradasCompradas;
        this.conciertosAsistidos = conciertosAsistidos;
        this.valoraciones = valoraciones;
    }

    public Usuario() {
    }
    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Entrada> getEntradasCompradas() {
        return entradasCompradas;
    }

    public void setEntradasCompradas(ArrayList<Entrada> entradasCompradas) {
        this.entradasCompradas = entradasCompradas;
    }

    public HashSet<Concierto> getConciertosAsistidos() {
        return conciertosAsistidos;
    }

    public void setConciertosAsistidos(HashSet<Concierto> conciertosAsistidos) {
        this.conciertosAsistidos = conciertosAsistidos;
    }

    public HashMap<Concierto, Integer> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(HashMap<Concierto, Integer> valoraciones) {
        this.valoraciones = valoraciones;
    }
    // Método comprarEntrada
    public void comprarEntrada(Concierto concierto, Entrada.TipoEntrada tipo) {
        // 1. Comprobar si el concierto está activo
        if (!concierto.isActivo()) {
            System.out.println("Error: El concierto no está activo");
            return;
        }

        // 2. Comprobar si el usuario ya ha asistido al concierto
        if (conciertosAsistidos.contains(concierto)) {
            System.out.println("Error: Ya has asistido a este concierto");
            return;
        }

        // 3. Comprobar si hay entradas disponibles
        if (!concierto.entradasDisponibles()) {
            System.out.println("Error: No hay entradas disponibles");
            return;
        }

        // 4. Crear el objeto Entrada
        Entrada nuevaEntrada = new Entrada(concierto, tipo);

        // 5. Añadir la entrada al concierto
        concierto.getEntradasVendidas().add(nuevaEntrada);

        // 6. Añadir la entrada al usuario
        entradasCompradas.add(nuevaEntrada);

        // 7. Añadir el concierto a conciertos asistidos
        conciertosAsistidos.add(concierto);

        System.out.println("Entrada comprada exitosamente: " + nuevaEntrada);
    }
    // Metodo valorar
    public void valorar(Concierto concierto, int valoracion) {
        // 1. Comprobar si el concierto está en la lista de asistidos
        if (!conciertosAsistidos.contains(concierto)) {
            System.out.println("Error: No has asistido a este concierto");
            return;
        }
        // 2. Comprobar que la valoración está entre 0 y 10
        if (valoracion < 0 || valoracion > 10) {
            System.out.println("Error: La valoración debe estar entre 0 y 10");
            return;
        }
        // 3. Añadir o modificar la valoración
        valoraciones.put(concierto, valoracion);

        System.out.println("Valoración registrada: " + valoracion + "/10 para " + concierto);
    }
    //Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return edad == usuario.edad && Objects.equals(nombre, usuario.nombre) && Objects.equals(entradasCompradas, usuario.entradasCompradas) && Objects.equals(conciertosAsistidos, usuario.conciertosAsistidos) && Objects.equals(valoraciones, usuario.valoraciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, edad, entradasCompradas, conciertosAsistidos, valoraciones);
    }
    //Representacion del texto
    @Override
    public String toString() {
        return this.nombre + " (ha asistido a " + this.conciertosAsistidos.size() + " conciertos)";
    }
}
