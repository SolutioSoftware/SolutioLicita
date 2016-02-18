/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.InstituicaoLicitadora;
import br.com.solutiolicita.persistencia.DaoGenerico;
import br.com.solutiolicita.persistencia.DaoIF;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoInstituicaoLicitadora implements ServicoInstituicaoLicitadoraIF{
    
    private final DaoIF<InstituicaoLicitadora> dao = new DaoGenerico(InstituicaoLicitadora.class);
    
    @Inject
    private EntityManager entityManager;
    
    public ServicoInstituicaoLicitadora(){
    }

    @Override
    public void criar(InstituicaoLicitadora entidade) {
        dao.setEntityManager(entityManager);
        dao.criar(entidade);
    }

    @Override
    public void remover(InstituicaoLicitadora entidade) {
        dao.setEntityManager(entityManager);
        dao.remover(entidade);
    }

    @Override
    public void atualizar(InstituicaoLicitadora entidade) {
        dao.setEntityManager(entityManager);
        dao.atualizar(entidade);
    }

    @Override
    public InstituicaoLicitadora buscarPorId(Long id) {
        dao.setEntityManager(entityManager);
        return dao.buscarPorId(id);
    }

    @Override
    public List<InstituicaoLicitadora> buscarTodos() {
        return null;
    }
    
}
