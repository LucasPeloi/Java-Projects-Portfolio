package peloi.lucas.repository;

import peloi.lucas.model.Conta;

import java.util.List;
import java.util.Optional;

public interface ContaRepository {

    void salvar(Conta conta);
    Optional<Conta> buscarPorNumero(String numero);

    List<Conta> listar();

    List<Conta> listarPorCpf(String cpf);

    void remover(String numero);

}
