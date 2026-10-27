package peloi.lucas.repository;

import peloi.lucas.model.Cliente;

import java.util.*;

public class ClienteRepositoryMemoria implements ClienteRepository {

    private final Map<String, Cliente> clientes = new HashMap<>();

    @Override
    public void salvar(Cliente cliente) {
        clientes.put(cliente.getCpf(), cliente);
    }

    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {
        return Optional.ofNullable(clientes.get(cpf));
    }

    @Override
    public List<Cliente> listar() {
        return new ArrayList<>(clientes.values());
    }

    @Override
    public void remover(String cpf) {
        clientes.remove(cpf);
    }

    @Override
    public boolean existe(String cpf) {
        return clientes.containsKey(cpf);
    }

}
