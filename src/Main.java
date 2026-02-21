import exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        // Crear conciertos y usuarios
        Concierto concierto1 = new Concierto("Coldplay",  "Madrid",    50.0, 5000, new ArrayList<>(), true);
        Concierto concierto2 = new Concierto("Rosalía",   "Barcelona", 45.0, 3000, new ArrayList<>(), true);
        Concierto concierto3 = new Concierto("Metallica", "Sevilla",   60.0, 8000, new ArrayList<>(), true);
        // Concierto inactivo para provocar ConciertoInactivoException
        Concierto conciertoInactivo = new Concierto("Prueba", "Valencia", 30.0, 100, new ArrayList<>(), false);
        conciertoInactivo.setActivo(false);

        // Concierto con aforo 1 para provocar AforoCompletoException
        Concierto conciertoLleno = new Concierto("CapacidadUno", "Bilbao", 40.0, 1, new ArrayList<>(), true);

        Usuario usuario1 = new Usuario("Ana García",       25, new ArrayList<>(), new HashSet<>(), new HashMap<>());
        Usuario usuario2 = new Usuario("Pedro López",      30, new ArrayList<>(), new HashSet<>(), new HashMap<>());
        Usuario usuario3 = new Usuario("María Rodríguez",  28, new ArrayList<>(), new HashSet<>(), new HashMap<>());


        // Compras normales
        System.out.println("COMPRA DE ENTRADAS");

        try { usuario1.comprarEntrada(concierto1, Entrada.TipoEntrada.VIP);   } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        try { usuario1.comprarEntrada(concierto2, Entrada.TipoEntrada.PISTA); } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }

        try { usuario2.comprarEntrada(concierto2, Entrada.TipoEntrada.GRADA); } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        try { usuario2.comprarEntrada(concierto3, Entrada.TipoEntrada.VIP);   } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }

        try { usuario3.comprarEntrada(concierto1, Entrada.TipoEntrada.PISTA); } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        try { usuario3.comprarEntrada(concierto3, Entrada.TipoEntrada.GRADA); } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }

        // PROVOCAR EXCEPCIONES
        System.out.println("PROVOCANDO EXCEPCIONES");

        // 1. ConciertoInactivoException
        System.out.println("ConciertoInactivoException");
        try {
            usuario1.comprarEntrada(conciertoInactivo, Entrada.TipoEntrada.GRADA);
        } catch (ConciertoInactivoException e) {
            System.out.println("¡Ups! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        // 2. ConciertoYaAsistidoException
        System.out.println("ConciertoYaAsistidoException");
        try {
            usuario1.comprarEntrada(concierto1, Entrada.TipoEntrada.PISTA);
        } catch (ConciertoYaAsistidoException e) {
            System.out.println("¡Ups! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        // 3. AforoCompletoException
        System.out.println("AforoCompletoException");
        try {
            usuario1.comprarEntrada(conciertoLleno, Entrada.TipoEntrada.PISTA);
            usuario2.comprarEntrada(conciertoLleno, Entrada.TipoEntrada.PISTA);
        } catch (AforoCompletoException e) {
            System.out.println("¡Ups! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        // 4. ConciertoNoAsistidoException
        System.out.println("ConciertoNoAsistidoException");
        try {
            usuario1.valorar(concierto3, 7);
        } catch (ConciertoNoAsistidoException e) {
            System.out.println("¡Ups! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        // 5. ValoracionIncorrecta
        System.out.println("ValoracionIncorrecta");
        try {
            usuario1.valorar(concierto1, 15);
        } catch (ValoracionIncorrecta e) {
            System.out.println("¡Ups! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        // 6. CeroEntradasException
        System.out.println("CeroEntradasException");
        Concierto conciertoSinEntradas = new Concierto("NuevoArtista", "Granada", 35.0, 500, new ArrayList<>(), true);
        try {
            double media = conciertoSinEntradas.calcularPrecioMedio();
        } catch (CeroEntradasException e) {
            System.out.println("¡Ups! " + e.getMessage());
        }

        // VALORACIONES
        System.out.println("VALORACIONES");
        try { usuario1.valorar(concierto1, 9);  } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        try { usuario2.valorar(concierto3, 8);  } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        try { usuario3.valorar(concierto1, 10); } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }


        // ESTADÍSTICAS
        System.out.println("ESTADÍSTICAS");
        Concierto[] conciertos = {concierto1, concierto2, concierto3};

        int totalEntradas = 0, pista = 0, grada = 0, vip = 0;
        double recaudacionTotal = 0;
        Concierto masVendido = concierto1, menosVendido = concierto1;

        for (Concierto c : conciertos) {
            int vendidas = c.getEntradasVendidas().size();
            totalEntradas += vendidas;
            recaudacionTotal += c.calcularRecaudacion();

            if (vendidas > masVendido.getEntradasVendidas().size()) masVendido = c;
            if (vendidas < menosVendido.getEntradasVendidas().size()) menosVendido = c;

            for (Entrada e : c.getEntradasVendidas()) {
                switch (e.getTipo()) {
                    case PISTA -> pista++;
                    case GRADA -> grada++;
                    case VIP   -> vip++;
                }
            }
        }

        double precioMedio = totalEntradas == 0 ? 0 : recaudacionTotal / totalEntradas;

        System.out.println("Entradas vendidas totales: " + totalEntradas);
        System.out.println("Pista: " + pista + ", Grada: " + grada + ", VIP: " + vip);
        System.out.println("Recaudación total: " + recaudacionTotal + " €");
        System.out.println("Precio medio: " + precioMedio + " €");
        System.out.println("Concierto con más entradas:  " + masVendido   + " (" + masVendido.getEntradasVendidas().size()   + ")");
        System.out.println("Concierto con menos entradas: " + menosVendido + " (" + menosVendido.getEntradasVendidas().size() + ")");
    }
}
