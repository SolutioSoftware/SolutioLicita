package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.Login;
import br.com.solutiolicita.servicos.ServicoLoginIF;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Matheus Oliveira
 */

@Named
@SessionScoped
public class ControladorLogin implements Serializable{
    
    private Login login;
    
    @Inject
    private ServicoLoginIF servicoLogin;
    
    public ControladorLogin(){
    }
    
    @PostConstruct
    public void init(){
        login = new Login();
    }
    
}
