package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.InstituicaoLicitadora;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoInstituicaoLicitadora implements ServicoInstituicaoLicitadoraIF{
    
    @Inject
    private DaoIF<InstituicaoLicitadora> dao;
    
    public ServicoInstituicaoLicitadora(){
    }

    @Override
    @Transactional
    public void criar(InstituicaoLicitadora entidade) {
        dao.criar(entidade);
    }

    @Override
    @Transactional
    public void remover(InstituicaoLicitadora entidade) {
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(InstituicaoLicitadora entidade) {
        dao.atualizar(entidade);
    }

    @Override
    public InstituicaoLicitadora buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<InstituicaoLicitadora> buscarTodos() {
        return dao.consultar("InstituicaoLicitadora.findAll");
    }
    
}
