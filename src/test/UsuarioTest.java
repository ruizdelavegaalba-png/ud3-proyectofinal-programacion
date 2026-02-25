package test;
import clases.Concierto;
import clases.Entrada;
import clases.Usuario;
import exceptions.ConciertoNoAsistidoException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void testValorarConcierto() throws Exception {
        Concierto concierto = new Concierto("Artista Test", "Ciudad Test", 50.0, 100, new ArrayList<>(), true);
        Usuario usuario = new Usuario("Test User", 20, new ArrayList<>(), new HashSet<>(), new HashMap<>());

        usuario.comprarEntrada(concierto, Entrada.TipoEntrada.PISTA);
        usuario.valorar(concierto, 8);

        assertTrue(usuario.getValoraciones().containsKey(concierto));
        assertEquals(8, usuario.getValoraciones().get(concierto));
    }

    @Test
    void testValorarSinAsistirLanzaExcepcion() {
        Concierto concierto = new Concierto("Artista Test", "Ciudad Test", 50.0, 100, new ArrayList<>(), true);
        Usuario usuario = new Usuario("Test User", 20, new ArrayList<>(), new HashSet<>(), new HashMap<>());

        assertThrows(ConciertoNoAsistidoException.class, () -> usuario.valorar(concierto, 7));
    }
}