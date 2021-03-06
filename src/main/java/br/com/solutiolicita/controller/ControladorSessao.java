package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Lance;
import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.servicos.ServicoSessaoIF;
import java.util.ArrayList;
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

    private List<Lance> lances;

    private List<ItemPregao> itensPregao;

    @Inject
    private ServicoSessaoIF servicoSessao;

    private final String STATUS_ENCERRADA = "Encerrada";

    public ControladorSessao() {
    }

    @PostConstruct
    public void iniciar() {
        sessao = new Sessao();
        lances = new ArrayList();
        itensPregao = new ArrayList();
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
        try {
            if (sessao.getId() == null) {
                sessao.setStatusSessao("Iniciada");
                servicoSessao.criar(sessao);
            } else {
                sessao.setStatusSessao("Iniciada");
                sessao.setHorarioInicio(new Date());
                servicoSessao.atualizar(sessao);
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessao", sessao);
            return "sessaoIniciar.xhtml";
        } catch (RollbackException re) {
            JsfUtil.addErrorMessage("Já existe uma sessão para este pregão.");
        }
        return "/restrito/sessao/sessaoSalvar.xhtml";
    }

    public void remover() {
        try {
            servicoSessao.remover(sessao);
        } catch (RollbackException re) {
            JsfUtil.addErrorMessage("Sessão não pode ser removida.");
        }
    }

    public String buscarResultados() {
        itensPregao = servicoSessao.buscarItensPregao(sessao.getIdPregao());
        return "/restrito/pregao/pregaoFinalizado.xhtml";
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

    public String getStatusEncerrada() {
        return STATUS_ENCERRADA;
    }

    public List<Lance> getLances() {
        return lances;
    }

    public List<ItemPregao> getItensPregao() {
        return itensPregao;
    }

    public List<Lance> getLances(ItemPregao itemPregao) {
        return servicoSessao.buscarLances(itemPregao);
    }

}
