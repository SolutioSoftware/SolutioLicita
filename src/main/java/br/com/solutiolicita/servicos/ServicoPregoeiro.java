/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.Pregoeiro;
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
public class ServicoPregoeiro implements ServicoPregoeiroIF{
    
    private final DaoIF<Pregoeiro> dao = new DaoGenerico(Pregoeiro.class);
    
    @Inject
    private EntityManager entityManager;
    
    public ServicoPregoeiro(){
    }

    @Override
    @Transactional
    public void criar(Pregoeiro entidade) {
        dao.setEntityManager(entityManager);
        dao.criar(entidade);
    }

    @Override
    @Transactional
    public void remover(Pregoeiro entidade) {
        dao.setEntityManager(entityManager);
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(Pregoeiro entidade) {
        dao.setEntityManager(entityManager);
        dao.atualizar(entidade);
    }

    @Override
    public Pregoeiro buscarPorId(Long id) {
        dao.setEntityManager(entityManager);
        return dao.buscarPorId(id);
    }

    @Override
    public List<Pregoeiro> buscarTodos() {
        dao.setEntityManager(entityManager);
        return dao.consultar("Pregoeiro.findAll");
    }
    
}
