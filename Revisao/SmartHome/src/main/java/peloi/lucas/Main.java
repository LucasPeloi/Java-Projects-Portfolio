package peloi.lucas;

import peloi.lucas.dao.DispositivoDAO;
import peloi.lucas.database.DatabaseInitializer;
import peloi.lucas.exception.SobrecargaEletricaException;
import peloi.lucas.model.ArCondicionado;
import peloi.lucas.model.Comodo;

public class Main {
    static void main() {
        DatabaseInitializer.criarTabelas();
        DispositivoDAO dispositivoDAO = new DispositivoDAO();
        Comodo comodo = new Comodo("Sala", 2000);
        ArCondicionado arCondicionado01 = new ArCondicionado("a", 1500,true,21);
        ArCondicionado arCondicionado02 = new ArCondicionado("b", 1500,true,19);

        try {
            comodo.instalarDispositivo(arCondicionado01);
            comodo.instalarDispositivo(arCondicionado02);
        }catch (SobrecargaEletricaException e){
            System.out.println("Carga máxima ultrapassada");
        }

        dispositivoDAO.inserir(arCondicionado01);
        dispositivoDAO.inserir(arCondicionado02);
        dispositivoDAO.listarDispostivos();
    }
}
