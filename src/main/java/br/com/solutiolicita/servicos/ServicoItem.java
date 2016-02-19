package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesRunTimeLicita;
import br.com.solutiolicita.modelos.Item;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoItem implements ServicoItemIF {

    @Inject
    private DaoIF<Item> dao;

    public ServicoItem() {
    }

    public DaoIF getDao() {
        return dao;
    }

    @Override
    public Item buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<Item> buscarTodos() {
        return dao.consultar("Item.findAll");
    }

    @Override
    @Transactional
    public void criar(Item entidade) {
        try {
            getDao().criar(entidade);
        } catch (ExcecoesRunTimeLicita el) {
            System.out.println(el.getMessage());
        }
    }

    @Override
    @Transactional
    public void remover(Item entidade) {
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(Item entidade) {
        dao.atualizar(entidade);
    }

}
