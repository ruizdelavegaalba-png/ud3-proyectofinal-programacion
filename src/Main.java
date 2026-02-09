import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        // Crear 3 conciertos
        Concierto concierto1 = new Concierto("Coldplay", "Madrid", 50.0, 5000,ArrayList<>(),true );
        Concierto concierto2 = new Concierto("Rosalía", "Barcelona", 45.0, 3000,ArrayList<>(),true);
        Concierto concierto3 = new Concierto("Metallica", "Sevilla", 60.0, 8000,ArrayList<>(),true);

        // Crear 3 usuarios
        Usuario usuario1 = new Usuario("Ana García", 25, ArrayList<>(), HashSet<>(),HashMap<>());
        Usuario usuario2 = new Usuario("Pedro López", 30,ArrayList<>(), HashSet<>(),HashMap<>());
        Usuario usuario3 = new Usuario("María Rodríguez", 28,ArrayList<>(), HashSet<>(),HashMap<>());

        // Hacer que cada usuario compre entradas para 2 conciertos
        // Usuario 1 compra 2 entradas
        System.out.println(usuario1.getNombre() + ":");
        usuario1.comprarEntrada(concierto1, Entrada.TipoEntrada.VIP);
        usuario1.comprarEntrada(concierto2, Entrada.TipoEntrada.PISTA);

        // Usuario 2 compra 2 entradas
        System.out.println(usuario2.getNombre() + ":");
        usuario2.comprarEntrada(concierto2, Entrada.TipoEntrada.GRADA);
        usuario2.comprarEntrada(concierto3, Entrada.TipoEntrada.VIP);

        // Usuario 3 compra 2 entradas
        System.out.println(usuario3.getNombre() + ":");
        usuario3.comprarEntrada(concierto1, Entrada.TipoEntrada.PISTA);
        usuario3.comprarEntrada(concierto3, Entrada.TipoEntrada.GRADA);
        // Hacer que cada usuario valore 1 concierto
        System.out.println("VALORACIONES");

        System.out.println(usuario1.getNombre() + ":");
        usuario1.valorar(concierto1, 9);

        System.out.println(usuario2.getNombre() + ":");
        usuario2.valorar(concierto3, 8);

        System.out.println(usuario3.getNombre() + ":");
        usuario3.valorar(concierto1, 10);

        // ESTADÍSTICAS
        System.out.println("ESTADÍSTICAS GENERALES");

        // Número de entradas vendidas en total
        int entradasTotales = concierto1.getEntradasVendidas().size() +
                concierto2.getEntradasVendidas().size() +
                concierto3.getEntradasVendidas().size();
        System.out.println("Número total de entradas vendidas: " + entradasTotales);

        // Número de entradas vendidas de cada tipo
        int entradasPista = 0;
        int entradasGrada = 0;
        int entradasVIP = 0;

        // Contar entradas del concierto1
        for (Entrada entrada : concierto1.getEntradasVendidas()) {
            if (entrada.getTipo() == Entrada.TipoEntrada.PISTA) entradasPista++;
            else if (entrada.getTipo() == Entrada.TipoEntrada.GRADA) entradasGrada++;
            else if (entrada.getTipo() == Entrada.TipoEntrada.VIP) entradasVIP++;
        }

        // Contar entradas del concierto2
        for (Entrada entrada : concierto2.getEntradasVendidas()) {
            if (entrada.getTipo() == Entrada.TipoEntrada.PISTA) entradasPista++;
            else if (entrada.getTipo() == Entrada.TipoEntrada.GRADA) entradasGrada++;
            else if (entrada.getTipo() == Entrada.TipoEntrada.VIP) entradasVIP++;
        }

        // Contar entradas del concierto3
        for (Entrada entrada : concierto3.getEntradasVendidas()) {
            if (entrada.getTipo() == Entrada.TipoEntrada.PISTA) entradasPista++;
            else if (entrada.getTipo() == Entrada.TipoEntrada.GRADA) entradasGrada++;
            else if (entrada.getTipo() == Entrada.TipoEntrada.VIP) entradasVIP++;
        }

        System.out.println("Entradas vendidas por tipo:");
        System.out.println("- Pista: " + entradasPista);
        System.out.println("- Grada: " + entradasGrada);
        System.out.println("- VIP: " + entradasVIP);

        // Recaudación total
        double recaudacionTotal = concierto1.calcularRecaudacion() +
                concierto2.calcularRecaudacion() +
                concierto3.calcularRecaudacion();
        System.out.println("Recaudación total: " + recaudacionTotal + " €");

        // Precio medio de todas las entradas
        double precioMedioTotal = 0.0;
        if (entradasTotales > 0) {
            precioMedioTotal = recaudacionTotal / entradasTotales;
        }
        System.out.println("Precio medio de todas las entradas: " + precioMedioTotal + " €");

        // Concierto con más entradas vendidas
        Concierto conciertoMax = concierto1;
        if (concierto2.getEntradasVendidas().size() > conciertoMax.getEntradasVendidas().size()) {
            conciertoMax = concierto2;
        }
        if (concierto3.getEntradasVendidas().size() > conciertoMax.getEntradasVendidas().size()) {
            conciertoMax = concierto3;
        }
        System.out.println("Concierto con más entradas vendidas: " + conciertoMax +
                " (" + conciertoMax.getEntradasVendidas().size() + " entradas)");

        // Concierto con menos entradas vendidas
        Concierto conciertoMin = concierto1;
        if (concierto2.getEntradasVendidas().size() < conciertoMin.getEntradasVendidas().size()) {
            conciertoMin = concierto2;
        }
        if (concierto3.getEntradasVendidas().size() < conciertoMin.getEntradasVendidas().size()) {
            conciertoMin = concierto3;
        }
        System.out.println("Concierto con menos entradas vendidas: " + conciertoMin +
                " (" + conciertoMin.getEntradasVendidas().size() + " entradas)");
    }
}