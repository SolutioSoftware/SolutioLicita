package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.servicos.ServicoSessaoIF;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.RollbackException;

/**
 *
 * @author Matheus Oliveira
 */
@Model
public class ControladorSessao {

    private Sessao sessao;

    @Inject
    private ServicoSessaoIF servicoSessao;
    
    private final String STATUS_ENCERRADA = "Encerrada";

    public ControladorSessao() {
    }

    @PostConstruct
    public void iniciar() {
        sessao = new Sessao();
    }

    public String criar() {
        try {
            sessao.setStatusSessao("Agendada");
            servicoSessao.criar(sessao);
        } catch (RollbackException re) {
            JsfUtil.addErrorMessage("Já existe uma sessão para este pregão.");
        }
        return "/restrito/sessao/sessao.xhtml";
    }

    public String prepararEditar() {
        return "/restrito/sessao/sessaoEditar.xhtml";
    }

    public String atualizar() {
        servicoSessao.atualizar(sessao);
        return "/restrito/sessao/sessao.xhtml?faces-redirect=true";
    }

    public String iniciarSessao() {
        if (sessao.getId() == null) {
            sessao.setStatusSessao("Iniciada");
            servicoSessao.criar(sessao);
        } else {
            sessao.setStatusSessao("Iniciada");
            servicoSessao.atualizar(sessao);
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessao", sessao);
        return "sessaoIniciar.xhtml";
    }

    public void remover() {
        try {
            servicoSessao.remover(sessao);
        } catch (RollbackException re) {
            JsfUtil.addErrorMessage("Sessão não pode ser removida.");
        }
    }
    
    public void buscarResultados(){
        JsfUtil.addSuccessMessage("Resultado da sessão: " + sessao.getStatusSessao());
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public List<Sessao> getSessoes() {
        return servicoSessao.buscarTodos();
    }

    public Date getDataAtual() {
        return new Date();
    }
    
    public String getStatusEncerrada(){
        return STATUS_ENCERRADA;
    }
    
}
