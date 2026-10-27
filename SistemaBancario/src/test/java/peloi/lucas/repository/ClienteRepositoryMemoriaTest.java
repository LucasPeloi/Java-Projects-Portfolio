package peloi.lucas.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peloi.lucas.model.Cliente;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteRepositoryMemoriaTest {

    private ClienteRepositoryMemoria repo;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        repo = new ClienteRepositoryMemoria();
        cliente = new Cliente("529.982.247-25", "Lucas", LocalDate.of(2000, 1, 1));
    }

    @Test
    void deveSalvarEBuscarPorCpf() {
        repo.salvar(cliente);

        assertTrue(repo.buscarPorCpf("52998224725").isPresent());
        assertEquals("Lucas", repo.buscarPorCpf("52998224725").get().getNome());
    }

    @Test
    void buscaDeCpfInexistenteDeveVirVazia() {
        assertTrue(repo.buscarPorCpf("00000000000").isEmpty());
    }

    @Test
    void deveRemoverCliente() {
        repo.salvar(cliente);
        repo.remover("52998224725");

        assertFalse(repo.existe("52998224725"));
        assertTrue(repo.listar().isEmpty());
    }

    @Test
    void listaRetornadaNaoDeveAlterarORepositorio() {
        repo.salvar(cliente);
        repo.listar().clear();

        assertEquals(1, repo.listar().size());
    }
}