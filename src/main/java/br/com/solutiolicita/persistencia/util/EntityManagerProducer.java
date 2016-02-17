/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.persistencia.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Matheus Oliveira
 */
@ApplicationScoped
public class EntityManagerProducer {

    private EntityManagerFactory entityManagerFactory;

    public EntityManagerProducer() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("solutio_licita");
    }

    @Produces
    @RequestScoped
    public EntityManager create() {
        return entityManagerFactory.createEntityManager();
    }

    public void close(@Disposes EntityManager entityManager) {
        entityManager.close();
    }

}
