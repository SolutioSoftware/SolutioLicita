package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.Proposta;
import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.servicos.ServicoSessaoIF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    private List<EmpresaLicitante> empresasParticipantes;
    private List<Proposta> propostasDoLicitante;

    @Inject
    private ServicoSessaoIF servicoSessao;

    public ControladorSessaoIniciar() {
    }

    @PostConstruct
    public void iniciar() {
        sessao = (Sessao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessao");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("sessao");
        empresasParticipantes = new ArrayList<>();
        propostasDoLicitante = new ArrayList<>();

        listarLicitantesParticipantes();
    }
    
    
    public void validarImportacaoArquivo(FileUploadEvent file){
        
        propostasPorLicitante();
        
        if (!getPropostasDoLicitante().isEmpty()) {
            try {
                getServicoSessao().removerPropostasPorLicitante(sessao, empresaLicitante);
            } catch (ExcecoesLicita ex) {
                Logger.getLogger(ControladorSessaoIniciar.class.getName()).log(Level.SEVERE, "Erro: {0}", ex.getMessage());
            }
            
            
        } 
        
        validarArquivoXLS(file);
        
    }

    public void validarArquivoXLS(FileUploadEvent file) {

        try {

            planilhaImport = file.getFile();
            List<Proposta> propostas;
            propostas = servicoSessao.validarArquivoXLS(planilhaImport, sessao.getIdPregao(), sessao, empresaLicitante);
            servicoSessao.salvarPropostar(propostas);
            propostasPorLicitante();
            JsfUtil.addSuccessMessage("Planilha Importada com Sucesso!");
            planilhaImport = null;
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

    public void adicionarEmpresa() {
        if (!getEmpresasParticipantes().contains(getEmpresaLicitante())) {
            getEmpresasParticipantes().add(getEmpresaLicitante());
            setEmpresaLicitante(new EmpresaLicitante());
        } else {
            // TODO
        }
        //Logger.getGlobal().log(Level.INFO, "Adicionando itemPregao {0}", itemPregao);
    }

    public void confirmarNovasPropostas() {
        try {
            getServicoSessao().removerPropostasPorLicitante(sessao, empresaLicitante);
        } catch (ExcecoesLicita ex) {
            Logger.getLogger(ControladorSessaoIniciar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelarNovasPropostas() {

        RequestContext.getCurrentInstance().closeDialog("confirmarNovasPropostas");
    }

    public void propostasPorLicitante() {

        try {
            getPropostasDoLicitante().removeAll(getPropostasDoLicitante());
            setPropostasDoLicitante(getServicoSessao().propostasPorLicitante(sessao, empresaLicitante));

        } catch (ExcecoesLicita ex) {
            Logger.getLogger(ControladorSessaoIniciar.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void listarLicitantesParticipantes() {

        List<Proposta> propostas = getServicoSessao().buscarPropostasPorSessao(getSessao());

        for (Proposta proposta : propostas) {

            if (!getEmpresasParticipantes().contains(proposta.getIdLicitante())) {
                getEmpresasParticipantes().add(proposta.getIdLicitante());
            }

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

    public List<EmpresaLicitante> getEmpresasParticipantes() {
        return empresasParticipantes;
    }

    public void setEmpresasParticipantes(List<EmpresaLicitante> empresasParticipantes) {
        this.empresasParticipantes = empresasParticipantes;
    }

    public List<Proposta> getPropostasDoLicitante() {

        return propostasDoLicitante;
    }

    public void setPropostasDoLicitante(List<Proposta> propostasDoLicitante) {
        this.propostasDoLicitante = propostasDoLicitante;
    }

}
