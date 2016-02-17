/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.persistencia.util;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Matheus Oliveira
 */

@Interceptor
@Transactional
public class InterceptadorTransacao implements Serializable{
    
    
    private static final long serialVersionUID = 1L;

    @Inject
    private transient EntityManager entityManager;

    /* The annotation @AroundInvoke sign this method for to be called automatically before the 
     method annotated with @Transactional */
    @AroundInvoke
    public Object invoke(InvocationContext context) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        boolean owner = false;

        try {
            if (!entityTransaction.isActive()) {
                //A way to grant any operation pending will be rollback, preventing possible errors.
                entityTransaction.begin();
                entityTransaction.rollback();

                // Now, after check about posible errors, the transaction is started.
                entityTransaction.begin();
                owner = true;
            }
            //After  transaction started, the content of any method intercepted as @Transactional is run.
            return context.proceed();
        } catch (Exception e) {
            if (entityTransaction != null && owner) {
                //Any anomaly operation and the changes are undone
                entityTransaction.rollback();
            }
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(InterceptadorTransacao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (entityTransaction != null && entityTransaction.isActive() && owner) {
                //Finaly, without any errors, the changes are sended to the database.
                entityTransaction.commit();
            }
        }
        return null;
    }
    
}
