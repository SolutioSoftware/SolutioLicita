package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.JsfUtil;
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
@SessionScoped
@Named
public class ControladorLogin implements Serializable{

    private Login login;

    @Inject
    private ServicoLoginIF servicoLogin;

    public ControladorLogin() {
    }

    @PostConstruct
    public void init() {
        login = new Login();
    }

    public String acessarSistema() {
        if (servicoLogin.verificarDados(login.getUsuario(), login.getSenha())) {
            return "/restrito/index.xhtml?faces-redirect=true";
        }
        JsfUtil.addErrorMessage("Usuário ou Senha Estão Incorretos!");
        return "/restrito/login/login.xhtml";
    }
    
    public String logout(){
        return "/restrito/login/login.xhtml?faces-redirect=true";
    }
    
    public Login getLogin(){
        return login;
    }
    
    public void setLogin(Login login){
        this.login = login;
    }

}
