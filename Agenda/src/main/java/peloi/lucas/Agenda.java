package peloi.lucas;

import java.util.ArrayList;
import java.util.Locale;

public class Agenda {
    ArrayList<Contato> agenda;

    public Agenda(){
        agenda = new ArrayList<>();
    }

    public void adicionarContato(Contato contato){
        agenda.add(contato);
        System.out.println("Contato " + contato.getNome() + " adicionado com sucesso!");
    }

    public void removerContato(int id){
        if (id >= 0 && id < agenda.size()) {
            Contato removido = agenda.remove(id);
            System.out.println(removido.getNome() + " removido!");
        } else {
            System.out.println("ID inválido.");
        }
    }

    public void listarContatos(){
        if (agenda.size() > 0) {
            System.out.println("ID        NOME        TELEFONE        EMAIL");
            for (Contato contato : agenda) {
                System.out.println(agenda.indexOf(contato) + " | " + contato.toString());
            }
        }else{
            System.out.println("Agenda Vazia!");
        }
    }

    public void buscarContato(String nome){
        boolean encontrado = false;

        for (Contato contato : agenda){
            if (contato.getNome().toUpperCase(Locale.ROOT).contains(nome.toUpperCase())){
                System.out.println(agenda.indexOf(contato) + " | " + contato.toString());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum contato encontrado.");
        }
    }

}
