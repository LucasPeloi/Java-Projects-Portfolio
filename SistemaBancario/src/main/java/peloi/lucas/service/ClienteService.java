package peloi.lucas.service;

import peloi.lucas.exception.ClienteComContasException;
import peloi.lucas.exception.ClienteNaoEncontradoException;
import peloi.lucas.exception.CpfDuplicadoException;
import peloi.lucas.model.Cliente;
import peloi.lucas.repository.ClienteRepository;
import peloi.lucas.repository.ContaRepository;
import peloi.lucas.util.ValidadorCpf;

import java.time.LocalDate;
import java.util.List;

public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;

    public ClienteService(ClienteRepository clienteRepository, ContaRepository contaRepository) {
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
    }

    public Cliente cadastrar(String cpf, String nome, LocalDate dataNascimento) {
        Cliente cliente = new Cliente(cpf, nome, dataNascimento);

        if (clienteRepository.existe(cliente.getCpf())) {
            throw new CpfDuplicadoException("Já existe cliente com o CPF " + cliente.getCpf());
        }

        clienteRepository.salvar(cliente);
        return cliente;
    }

    public List<Cliente> listar() {
        return clienteRepository.listar();
    }

    public Cliente buscar(String cpf) {
        String cpfLimpo = ValidadorCpf.limpar(cpf);
        return clienteRepository.buscarPorCpf(cpfLimpo)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado para o CPF " + cpfLimpo));
    }

    public Cliente atualizarNome(String cpf, String novoNome) {
        Cliente cliente = buscar(cpf);
        cliente.setNome(novoNome);
        clienteRepository.salvar(cliente);
        return cliente;
    }

    public void remover(String cpf) {
        Cliente cliente = buscar(cpf);

        if (!contaRepository.listarPorCpf(cliente.getCpf()).isEmpty()) {
            throw new ClienteComContasException(
                    "Cliente possui contas e não pode ser removido");
        }

        clienteRepository.remover(cliente.getCpf());
    }
}
