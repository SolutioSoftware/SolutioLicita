/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.util;

import br.com.solutiolicita.modelos.Pregoeiro;
import br.com.solutiolicita.persistencia.DaoGenerico;
import br.com.solutiolicita.persistencia.DaoIF;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author WitaloCarlos
 */
public class Licita {

    /**
     * @param args the command line arguments
     */
    
    private Licita(){
    }
    public static void main(String[] args) {
        
        DaoIF<Pregoeiro> dao = new DaoGenerico(Pregoeiro.class);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("solutio_licita");
        EntityManager em = emf.createEntityManager();
        
        Pregoeiro pregoeiro = new Pregoeiro();
        String senha = Criptografar.getInstance().criptografar("admin");
        pregoeiro.getLogin().setSenha(senha);
        pregoeiro.getLogin().setUsuario("admin");
        pregoeiro.getPessoaFisica().setCpf("12143343543");
        pregoeiro.getPessoaFisica().setNome("Matheus");
        pregoeiro.getPessoaFisica().setRg("1233123");
        
        em.getTransaction().begin();
        dao.setEntityManager(em);
        dao.criar(pregoeiro);
        em.getTransaction().commit();
        em.close();
        
    }
    
}
