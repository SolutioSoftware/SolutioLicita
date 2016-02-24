package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoPregao implements ServicoPregaoIF{
    
    @Inject
    private DaoIF<Pregao> dao;
    
    @Inject
    private DaoIF<ItemPregao> daoItemPregao;
    
    public ServicoPregao(){
    }

    @Override
    public List<ItemPregao> buscarItensPregoes(Pregao pregao) {
        String[] param = {"idPregao"};
        Object[] valores = {pregao};
        return daoItemPregao.consultar("ItemPregao.findByPregao", param , valores);
    }

    @Override
    @Transactional
    public void criar(Pregao entidade) {
        dao.criar(entidade);
    }

    @Override
    @Transactional
    public void remover(Pregao entidade) {
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(Pregao entidade) {
        dao.atualizar(entidade);
    }

    @Override
    public Pregao buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<Pregao> buscarTodos() {
        return dao.consultar("Pregao.findAll");
    }
    
}
