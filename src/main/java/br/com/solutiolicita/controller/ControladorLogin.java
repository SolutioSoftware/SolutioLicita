package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.Login;
import br.com.solutiolicita.modelos.Pregoeiro;
import br.com.solutiolicita.servicos.ServicoLoginIF;
import br.com.solutiolicita.servicos.ServicoPregoeiroIF;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.RollbackException;

/**
 *
 * @author Matheus Oliveira
 */
@SessionScoped
@Named
public class ControladorLogin implements Serializable {

    private Login login;
    private Pregoeiro pregoeiro;
    private String senhaAntiga;
    private String novaSenha;
    private String confirmarSenha;

    @Inject
    private ServicoLoginIF servicoLogin;

    @Inject
    private ServicoPregoeiroIF servicoPregoeiro;

    public ControladorLogin() {
    }

    @PostConstruct
    public void init() {
        login = new Login();
        pregoeiro = new Pregoeiro();
    }

    public String atualizar() {
        try {
            servicoLogin.verificarNovosValores(login, senhaAntiga, novaSenha, confirmarSenha);
            login.setSenha(novaSenha);
            servicoLogin.atualizar(login);
            limparCampos();
            JsfUtil.addSuccessMessage("Atualizado.");
        } catch (ExcecoesLicita el) {
            JsfUtil.addErrorMessage(el.getMessage());
            Logger.getGlobal().log(Level.WARNING, el.getMessage());
        } catch (RollbackException re){
            Logger.getGlobal().log(Level.WARNING, re.getMessage());
            JsfUtil.addErrorMessage("Login já existe!");
        }
        return null;
    }

    public String acessarSistema() {
        Login permissao = servicoLogin.verificarDados(login.getUsuario(), login.getSenha());
        if (permissao != null) {
            login = permissao;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", login);
            return "/restrito/index.xhtml?faces-redirect=true";
        }
        JsfUtil.addErrorMessage("Usuário ou Senha Estão Incorretos!");
        return "/restrito/login/login.xhtml";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/restrito/login/login.xhtml?faces-redirect=true";
    }

    public String criarPregoeiro() {
        try {
            servicoPregoeiro.criar(pregoeiro);
            limparCampos();
            return "/restrito/login/loginEditar.xhtml";
        } catch (RollbackException re) {
            Logger.getGlobal().log(Level.SEVERE, re.getMessage());
            JsfUtil.addErrorMessage("CPF/Login já existentes");
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            JsfUtil.addErrorMessage("Erro inesperado ocorreu!");
        }
        return null;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public Pregoeiro getPregoeiro() {
        return pregoeiro;
    }

    public void setPregoeiro(Pregoeiro pregoeiro) {
        this.pregoeiro = pregoeiro;
    }

    private void limparCampos() {
        senhaAntiga = "";
        novaSenha = "";
        confirmarSenha = "";
        pregoeiro = new Pregoeiro();
    }

    public List<Pregoeiro> getPregoeiros() {
        return servicoPregoeiro.buscarTodos();
    }

}
