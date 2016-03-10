package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.servicos.ServicoLicitanteIF;
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
public class ControladorLicitante {

    private EmpresaLicitante licitante;

    @Inject
    private ServicoLicitanteIF servicoLicitante;

    public ControladorLicitante() {
    }

    @PostConstruct
    public void init() {
        licitante = new EmpresaLicitante();
    }

    public String criar() {
        try {
            servicoLicitante.validarLicitante(licitante);
            servicoLicitante.criar(licitante);
            return "/restrito/licitantes/licitante.xhtml";
        } catch (ExcecoesLicita el) {
            JsfUtil.addErrorMessage(el.getMessage());
            Logger.getGlobal().log(Level.WARNING, el.getMessage());
        } catch (RollbackException re) {
            JsfUtil.addErrorMessage("CPNJ já existe!");
            Logger.getGlobal().log(Level.WARNING, re.getMessage());
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erro inesperado ocorreu!");
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    public String editar() {
        try {
            servicoLicitante.validarLicitante(licitante);
            servicoLicitante.atualizar(licitante);
            return "/restrito/licitantes/licitante.xhtml";
        } catch (ExcecoesLicita el) {
            JsfUtil.addErrorMessage(el.getMessage());
            Logger.getGlobal().log(Level.WARNING, el.getMessage());
        } catch (RollbackException re) {
            JsfUtil.addErrorMessage("CNPJ já Existe.");
            Logger.getGlobal().log(Level.WARNING, re.getMessage());
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erro inesperado ocorreu!");
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    public String prepararEditar() {
        return "/restrito/licitantes/licitanteEditar.xhtml";
    }

    public void remover() {
        try {
            servicoLicitante.remover(licitante);
        }catch(Exception e){
            JsfUtil.addErrorMessage("Erro inesperado ocorreu!");
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
    }

    public List<EmpresaLicitante> getLicitantes() {
        return servicoLicitante.buscarTodos();
    }

    public EmpresaLicitante getLicitante() {
        return licitante;
    }

    public void setLicitante(EmpresaLicitante licitante) {
        this.licitante = licitante;
    }

}
