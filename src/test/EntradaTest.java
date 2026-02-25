package test;
import clases.Concierto;
import clases.Entrada;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class EntradaTest {

    @Test
    void testPrecioTotalGrada() {
        Concierto concierto = new Concierto("Artista Test", "Ciudad Test", 50.0, 100, new ArrayList<>(), true);
        Entrada entrada = new Entrada(concierto, Entrada.TipoEntrada.GRADA);
        assertEquals(50.0, entrada.getPrecioTotal(), 0.001);
    }

    @Test
    void testPrecioTotalVIP() {
        Concierto concierto = new Concierto("Artista Test", "Ciudad Test", 50.0, 100, new ArrayList<>(), true);
        Entrada entrada = new Entrada(concierto, Entrada.TipoEntrada.VIP);
        assertEquals(60.0, entrada.getPrecioTotal(), 0.001);
    }
}