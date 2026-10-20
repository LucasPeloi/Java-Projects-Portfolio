package peloi.lucas.model;

import org.junit.jupiter.api.Test;
import peloi.lucas.exception.CpfInvalidoException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveCriarClienteComCpfValido() {
        Cliente c = new Cliente("529.982.247-25", "Lucas", LocalDate.of(2000, 1, 1));
        assertEquals("52998224725", c.getCpf());
    }

    @Test
    void deveLancarExcecaoParaCpfInvalido() {
        assertThrows(CpfInvalidoException.class,
                () -> new Cliente("111.111.111-11", "Lucas", LocalDate.of(2000, 1, 1)));
    }

    @Test
    void deveRejeitarNomeVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente("529.982.247-25", "  ", LocalDate.of(2000, 1, 1)));
    }
}