package peloi.lucas;

import peloi.lucas.dao.PersonagemDAO;
import peloi.lucas.database.DatabaseInitialize;
import peloi.lucas.model.Guerreiro;
import peloi.lucas.model.Mago;
import peloi.lucas.model.Personagem;

public class Main {
    static void main() {
        DatabaseInitialize.criarTabelas();
        Guerreiro guerreiro = new Guerreiro("Lucas", 200, 200, 100);
        Mago mago = new Mago("Kamilly", 200, 200, 50);

        PersonagemDAO personagemDAO = new PersonagemDAO();

        personagemDAO.salvar(guerreiro);
        personagemDAO.salvar(mago);
        System.out.println("Ambos salvos com sucesso!");

        for (Personagem personagem : personagemDAO.buscarTodos()) {
            System.out.println(personagem.getNome() + " - " + personagem.atacar());
        }

    }
}
