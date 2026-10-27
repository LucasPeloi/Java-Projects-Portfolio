package peloi.lucas.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peloi.lucas.model.Cliente;
import peloi.lucas.model.ContaCorrente;
import peloi.lucas.model.ContaPoupanca;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContaRepositoryMemoriaTest {

    private ContaRepositoryMemoria repo;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        repo = new ContaRepositoryMemoria();
        cliente = new Cliente("529.982.247-25", "Lucas", LocalDate.of(2000, 1, 1));
    }

    @Test
    void deveSalvarEBuscarPorNumero() {
        repo.salvar(new ContaCorrente("0001", cliente, BigDecimal.ZERO));

        assertTrue(repo.buscarPorNumero("0001").isPresent());
        assertTrue(repo.buscarPorNumero("9999").isEmpty());
    }

    @Test
    void deveListarContasDeUmCliente() {
        repo.salvar(new ContaCorrente("0001", cliente, BigDecimal.ZERO));
        repo.salvar(new ContaPoupanca("0002", cliente, new BigDecimal("0.005")));

        assertEquals(2, repo.listarPorCpf("52998224725").size());
        assertTrue(repo.listarPorCpf("11144477735").isEmpty());
    }

    @Test
    void deveRemoverConta() {
        repo.salvar(new ContaCorrente("0001", cliente, BigDecimal.ZERO));
        repo.remover("0001");

        assertTrue(repo.buscarPorNumero("0001").isEmpty());
    }
}