/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.persistencia;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Matheus Oliveira
 */
public interface DaoIF<T> {
    
    public boolean criar(T entidade);
    
    public boolean remover(T entidade);
    
    public boolean atualizar(T entidade);
    
    public T buscarPorId(Long id);
    
    public List<T> consultar(String namedQuery, String[] parametros, Object[] valores);
    
    public List<T> consultar(String namedQuery);
    
    public EntityManager getEntityManager();
    
    public void setEntityManager(EntityManager entityManager);
    
}
