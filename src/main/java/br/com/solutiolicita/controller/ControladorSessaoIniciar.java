package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.modelos.Proposta;
import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.servicos.ServicoSessaoIF;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Matheus Oliveira
 */
@Named
@ViewScoped
public class ControladorSessaoIniciar implements Serializable {

    private UploadedFile planilhaImport;
    private Sessao sessao;
    private EmpresaLicitante empresaLicitante;
    
    @Inject
    private ServicoSessaoIF servicoSessao;

    public ControladorSessaoIniciar() {
    }

    @PostConstruct
    public void iniciar() {
        sessao = (Sessao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessao");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("sessao");
    }

    public void validarArquivoXLS(FileUploadEvent file) {
        try {
            planilhaImport = file.getFile();
            List<Proposta> propostas;
            propostas = servicoSessao.validarArquivoXLS(planilhaImport, sessao.getIdPregao(), sessao, empresaLicitante);
            servicoSessao.salvarPropostar(propostas);
            JsfUtil.addSuccessMessage("Planilha está CORRETA!");
        } catch (ExcecoesLicita el) {
            planilhaImport = null;
            RequestContext.getCurrentInstance().update("sessaoIn_form:file-import-xls");
            JsfUtil.addErrorMessage(el.getMessage());
            Logger.getGlobal().log(Level.WARNING, el.getMessage());
        } catch (Exception e) {
            planilhaImport = null;
            RequestContext.getCurrentInstance().update("sessaoIn_form:file-import-xls");
            JsfUtil.addErrorMessage("ERROR 04 - A Planilha inserida possui alguma IRREGULARIDADE!");
            Logger.getGlobal().log(Level.WARNING, "ERROR 04 - A Planilha inserida possui alguma IRREGULARIDADE!");
        }
    }

    public void removerArquivo() {
        planilhaImport = null;
    }

    public UploadedFile getPlanilhaImport() {
        return planilhaImport;
    }

    public void setPlanilhaImport(UploadedFile planilhaImport) {
        this.planilhaImport = planilhaImport;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public EmpresaLicitante getEmpresaLicitante() {
        return empresaLicitante;
    }

    public void setEmpresaLicitante(EmpresaLicitante empresaLicitante) {
        this.empresaLicitante = empresaLicitante;
    }

    public ServicoSessaoIF getServicoSessao() {
        return servicoSessao;
    }

    public void setServicoSessao(ServicoSessaoIF servicoSessao) {
        this.servicoSessao = servicoSessao;
    }

}
