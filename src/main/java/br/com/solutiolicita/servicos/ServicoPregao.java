/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.persistencia.DaoGenerico;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoPregao implements ServicoPregaoIF{
    
    private final DaoIF<Pregao> dao = new DaoGenerico(Pregao.class);
    
    @Inject
    private EntityManager entityManager;
    
    public ServicoPregao(){
    }

    @Override
    public Set<ItemPregao> buscarItensPregoes(Pregao pregao) {
        return null;
    }

    @Override
    @Transactional
    public void criar(Pregao entidade) {
        dao.setEntityManager(entityManager);
        dao.criar(entidade);
    }

    @Override
    @Transactional
    public void remover(Pregao entidade) {
        dao.setEntityManager(entityManager);
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(Pregao entidade) {
        dao.setEntityManager(entityManager);
        dao.atualizar(entidade);
    }

    @Override
    public Pregao buscarPorId(Long id) {
        dao.setEntityManager(entityManager);
        return dao.buscarPorId(id);
    }

    @Override
    public List<Pregao> buscarTodos() {
        dao.setEntityManager(entityManager);
        return dao.consultar("Pregao.findAll");
    }
    
}
