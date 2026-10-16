package peloi.lucas.model;

import peloi.lucas.exception.CpfInvalidoException;
import peloi.lucas.util.ValidadorCpf;

import java.time.LocalDate;

public class Cliente {
    private final String cpf;
    private String nome;
    LocalDate dataNascimento;

    public Cliente(String cpf, String nome, LocalDate dataNascimento) {
        if (!ValidadorCpf.isValido(cpf)){
            throw new CpfInvalidoException("CPF invalido: " + cpf);
        }
        this.cpf = ValidadorCpf.limpar(cpf);
        setNome(nome);
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() { return cpf; }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        this.nome = nome.trim();
    }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}
