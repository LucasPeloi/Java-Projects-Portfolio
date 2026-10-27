package peloi.lucas.repository;

import peloi.lucas.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    void salvar(Cliente cliente);

    Optional<Cliente> buscarPorCpf(String cpf);

    List<Cliente> listar();

    void remover(String cpf);

    boolean existe(String cpf);

}
