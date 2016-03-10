package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.servicos.ServicoPregaoIF;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.RollbackException;

/**
 *
 * @author Matheus Oliveira
 */
@Model
public class ControladorPregao {

    private Pregao pregao;

    @Inject
    private ServicoPregaoIF servicoPregao;

    public ControladorPregao() {
    }

    @PostConstruct
    public void init() {
        pregao = new Pregao();
    }

    /*
        Metodos CRUD
     */
    public String criar() {
        try {
            servicoPregao.validarPregao(pregao);
            servicoPregao.criar(pregao);
            return "/restrito/pregao/pregao.xhtml?faces-redirect=true";
        } catch (ExcecoesLicita el) {
            JsfUtil.addErrorMessage(el.getMessage());
            Logger.getGlobal().log(Level.WARNING, el.getMessage());
        } catch (RollbackException re) {
            JsfUtil.addErrorMessage("Número do Processo/Pregão já existe!");
            Logger.getGlobal().log(Level.WARNING, re.getMessage());
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erro inesperado ocorreu!");
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
        return null;

    }

    public String editar() {
        try {
            servicoPregao.validarPregao(pregao);
            servicoPregao.atualizar(pregao);
            return "/restrito/pregao/pregao.xhtml?faces-redirect=true";
        } catch (ExcecoesLicita el) {
            JsfUtil.addErrorMessage(el.getMessage());
            Logger.getGlobal().log(Level.WARNING, el.getMessage());
        } catch (RollbackException re) {
            JsfUtil.addErrorMessage("Número do Processo/Pregão já existe!");
            Logger.getGlobal().log(Level.WARNING, re.getMessage());
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erro inesperado ocorreu!");
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    public String prepararEditar() {
        return "/restrito/pregao/pregaoEditar.xhtml";
    }

    public void remover() {
        try {
            servicoPregao.remover(pregao);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erro inesperado ocorreu!");
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
    }

    /*
        Metodos para lógicas de negócio
     */
    public String prepararAdicionarItens() {
        return "/restrito/pregao/pregaoAdicionarItens.xhtml?faces-redirect=true";
    }

    public List<Pregao> getPregoes() {
        return servicoPregao.buscarTodos();
    }

    public Pregao getPregao() {
        return pregao;
    }

    public void setPregao(Pregao pregao) {
        this.pregao = pregao;
    }

}
