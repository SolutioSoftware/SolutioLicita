/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.persistencia.DaoGenerico;
import br.com.solutiolicita.persistencia.DaoIF;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoLicitante implements ServicoLicitanteIF {

    private final DaoIF<EmpresaLicitante> dao = new DaoGenerico(EmpresaLicitante.class);

    @Inject
    private EntityManager entityManager;

    public ServicoLicitante() {
    }

    @Override
    public void criar(EmpresaLicitante entidade) {
        dao.setEntityManager(entityManager);
        dao.criar(entidade);
    }

    @Override
    public void remover(EmpresaLicitante entidade) {
        dao.setEntityManager(entityManager);
        dao.remover(entidade);
    }

    @Override
    public void atualizar(EmpresaLicitante entidade) {
        dao.setEntityManager(entityManager);
        dao.atualizar(entidade);
    }

    @Override
    public EmpresaLicitante buscarPorId(Long id) {
        dao.setEntityManager(entityManager);
        return dao.buscarPorId(id);
    }

    @Override
    public List<EmpresaLicitante> buscarTodos() {
        return null;
    }

}
