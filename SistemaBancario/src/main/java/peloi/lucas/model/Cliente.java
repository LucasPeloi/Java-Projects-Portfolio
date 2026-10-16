package peloi.lucas.model;

import java.time.LocalDate;

public class Cliente {
    private String cpf;
    private String nome;
    LocalDate dataNascimento;

    public Cliente(String cpf, String nome, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
