package peloi.lucas.dao;

import peloi.lucas.model.Partida;

import java.util.List;

public interface PartidaDAOInterface {
    public void salvar(Partida p);
    public void excluir(long id);
    public List<Partida> buscarTodas();
}
