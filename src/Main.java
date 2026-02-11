import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        // Crear 3 conciertos
        Concierto concierto1 = new Concierto("Coldplay", "Madrid", 50.0, 5000,new ArrayList<>(),true );
        Concierto concierto2 = new Concierto("Rosalía", "Barcelona", 45.0, 3000,new ArrayList<>(),true);
        Concierto concierto3 = new Concierto("Metallica", "Sevilla", 60.0, 8000,new ArrayList<>(),true);

        // Crear 3 usuarios
        Usuario usuario1 = new Usuario("Ana García", 25, new ArrayList<>(), new HashSet<>(),new HashMap<>());
        Usuario usuario2 = new Usuario("Pedro López", 30,new ArrayList<>(), new HashSet<>(),new HashMap<>());
        Usuario usuario3 = new Usuario("María Rodríguez", 28,new ArrayList<>(), new HashSet<>(),new HashMap<>());

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
        Concierto[] conciertos = {concierto1, concierto2, concierto3};

        int totalEntradas = 0;
        int pista = 0, grada = 0, vip = 0;
        double recaudacionTotal = 0;

        Concierto masVendido = concierto1;
        Concierto menosVendido = concierto1;

        for (Concierto c : conciertos) {
            int vendidas = c.getEntradasVendidas().size();
            totalEntradas += vendidas;
            recaudacionTotal += c.calcularRecaudacion();

            if (vendidas > masVendido.getEntradasVendidas().size()) {
                masVendido = c;
            }
            if (vendidas < menosVendido.getEntradasVendidas().size()) {
                menosVendido = c;
            }

            for (Entrada e : c.getEntradasVendidas()) {
                switch (e.getTipo()) {
                    case PISTA -> pista++;
                    case GRADA -> grada++;
                    case VIP -> vip++;
                }
            }
        }

        double precioMedio = totalEntradas == 0 ? 0 : recaudacionTotal / totalEntradas;

        System.out.println("Entradas vendidas totales: " + totalEntradas);
        System.out.println("Pista: " + pista + ", Grada: " + grada + ", VIP: " + vip);
        System.out.println("Recaudación total: " + recaudacionTotal + " €");
        System.out.println("Precio medio: " + precioMedio + " €");
        System.out.println("Concierto con más entradas: " + masVendido + " (" + masVendido.getEntradasVendidas().size() + ")");
        System.out.println("Concierto con menos entradas: " + menosVendido + " (" + menosVendido.getEntradasVendidas().size() + ")");
    }
}