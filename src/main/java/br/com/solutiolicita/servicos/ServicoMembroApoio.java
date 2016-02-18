/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.MembroApoio;
import br.com.solutiolicita.persistencia.DaoGenerico;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoMembroApoio implements ServicoMembroApoioIF{
    
    private final DaoIF<MembroApoio> dao = new DaoGenerico(MembroApoio.class);
    
    @Inject
    private EntityManager entityManager;
    
    public ServicoMembroApoio(){
    }

    @Override
    @Transactional
    public void criar(MembroApoio entidade) {
        dao.setEntityManager(entityManager);
        dao.criar(entidade);
    }

    @Override
    @Transactional
    public void remover(MembroApoio entidade) {
        dao.setEntityManager(entityManager);
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(MembroApoio entidade) {
        dao.setEntityManager(entityManager);
        dao.atualizar(entidade);
    }

    @Override
    public MembroApoio buscarPorId(Long id) {
        dao.setEntityManager(entityManager);
        return dao.buscarPorId(id);
    }

    @Override
    public List<MembroApoio> buscarTodos() {
        dao.setEntityManager(entityManager);
        return dao.consultar("MembroApoio.findAll");
    }
    
}
