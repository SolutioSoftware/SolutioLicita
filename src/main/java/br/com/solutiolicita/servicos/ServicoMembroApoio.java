package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.MembroApoio;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoMembroApoio implements ServicoMembroApoioIF{
    
    @Inject
    private DaoIF<MembroApoio> dao;
    
    public ServicoMembroApoio(){
    }

    @Override
    @Transactional
    public void criar(MembroApoio entidade) {
        dao.criar(entidade);
    }

    @Override
    @Transactional
    public void remover(MembroApoio entidade) {
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(MembroApoio entidade) {
        dao.atualizar(entidade);
    }

    @Override
    public MembroApoio buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<MembroApoio> buscarTodos() {
        return dao.consultar("MembroApoio.findAll");
    }
    
}
