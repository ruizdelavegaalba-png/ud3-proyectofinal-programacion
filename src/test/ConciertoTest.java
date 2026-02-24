import exceptions.CeroEntradasException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ConciertoTest {

    @Test
    void testCalcularPrecioMedioConEntradas() throws CeroEntradasException {
        Concierto concierto = new Concierto("Artista Test", "Ciudad Test", 50.0, 100, new ArrayList<>(), true);
        concierto.getEntradasVendidas().add(new Entrada(concierto, Entrada.TipoEntrada.GRADA));
        concierto.getEntradasVendidas().add(new Entrada(concierto, Entrada.TipoEntrada.VIP));

        assertEquals(55.0, concierto.calcularPrecioMedio(), 0.001);
    }

    @Test
    void testCalcularPrecioMedioSinEntradasLanzaExcepcion() {
        Concierto concierto = new Concierto("Artista Test", "Ciudad Test", 50.0, 100, new ArrayList<>(), true);
        assertThrows(CeroEntradasException.class, () -> concierto.calcularPrecioMedio());
    }
}