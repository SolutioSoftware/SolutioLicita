/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesRunTimeLicita;
import br.com.solutiolicita.modelos.Item;
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
public class ServicoItem implements ServicoItemIF {

    private final DaoIF<Item> dao = new DaoGenerico(Item.class);
    
    @Inject
    private EntityManager entityManager;
    
    public ServicoItem(){
    }
    
    public DaoIF getDao() {
        return dao;
    }

    @Override
    public Item buscarPorId(Long id) {
        dao.setEntityManager(entityManager);
        return dao.buscarPorId(id);
    }

    @Override
    public List<Item> buscarTodos() {
        return null;
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    @Transactional
    public void criar(Item entidade) {
        try {
            System.out.println("INFO: SERVICOABSTRATO.CLASS: TRANSAÇÃO INICIADA");
            getDao().setEntityManager(entityManager);
            getDao().criar(entidade);
        } catch (ExcecoesRunTimeLicita el) {
            System.out.println(el.getMessage());
        }
    }

    @Override
    @Transactional
    public void remover(Item entidade) {
        dao.setEntityManager(entityManager);
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(Item entidade) {
        dao.setEntityManager(entityManager);
        dao.atualizar(entidade);
    }

}
