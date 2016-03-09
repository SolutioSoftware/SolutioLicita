package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.InstituicaoLicitadora;
import br.com.solutiolicita.servicos.ServicoInstituicaoLicitadoraIF;
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
public class ControladorLicitador {

    private InstituicaoLicitadora licitadora;

    @Inject
    private ServicoInstituicaoLicitadoraIF servicoLicitadora;

    public ControladorLicitador() {
    }

    @PostConstruct
    public void init() {
        licitadora = new InstituicaoLicitadora();
    }

    public String criar() {
        try {
            servicoLicitadora.validarLicitador(licitadora);
            servicoLicitadora.criar(licitadora);
            return "/restrito/licitadores/licitador.xhtml";
        } catch (ExcecoesLicita el) {
            JsfUtil.addErrorMessage(el.getMessage());
        } catch (RollbackException re) {
            JsfUtil.addErrorMessage("CPF já existe!");
            Logger.getGlobal().log(Level.WARNING, re.getMessage());
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ocorreu um erro inesperado!");
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    public String editar() {
        try {
            servicoLicitadora.validarLicitador(licitadora);
            servicoLicitadora.atualizar(licitadora);
            return "/restrito/licitadores/licitador.xhtml";
        } catch (ExcecoesLicita el) {
            JsfUtil.addErrorMessage(el.getMessage());
        } catch (RollbackException e) {
            JsfUtil.addErrorMessage("CNPJ já existe!");
            Logger.getGlobal().log(Level.WARNING, "Usuário tentou utilizar um CNPJ já existente.");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ocorreu um erro inesperado.");
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    public String prepararEditar() {
        return "/restrito/licitadores/licitadorEditar.xhtml";
    }

    public void remover() {
        servicoLicitadora.remover(licitadora);
    }

    public List<InstituicaoLicitadora> getLicitadoras() {
        return servicoLicitadora.buscarTodos();
    }

    public InstituicaoLicitadora getLicitadora() {
        return licitadora;
    }

    public void setLicitadora(InstituicaoLicitadora licitadora) {
        this.licitadora = licitadora;
    }

}
