package peloi.lucas.repository;

import peloi.lucas.model.Conta;

import java.util.*;

public class ContaRepositoryMemoria implements ContaRepository {

    private final Map<String, Conta> contas = new LinkedHashMap<>();

    @Override
    public void salvar(Conta conta) {
        contas.put(conta.getNumero(), conta);
    }

    @Override
    public Optional<Conta> buscarPorNumero(String numero) {
        return Optional.ofNullable(contas.get(numero));
    }

    @Override
    public List<Conta> listar() {
        return new ArrayList<>(contas.values());
    }

    @Override
    public List<Conta> listarPorCpf(String cpf) {
        return contas.values().stream()
                .filter(c -> c.getTitular().getCpf().equals(cpf))
                .toList();
    }

    @Override
    public void remover(String numero) {
        contas.remove(numero);
    }
}
