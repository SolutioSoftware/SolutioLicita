package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.servicos.ServicoSessaoIF;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author Matheus Oliveira
 */

@Named
@ViewScoped
public class ControladorSessaoIniciar implements Serializable{
    
    private UploadedFile planilhaImport;
    private Sessao sessao;
    private EmpresaLicitante empresaLicitante;
    private Pregao pregao;
    
    @Inject
    private transient ServicoSessaoIF servicoSessao;
    
    public ControladorSessaoIniciar(){
    }
    
    @PostConstruct
    public void iniciar(){
        pregao = new Pregao();
        sessao = (Sessao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessao");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("sessao");
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

    public Pregao getPregao() {
        return pregao;
    }

    public void setPregao(Pregao pregao) {
        this.pregao = pregao;
    }

    public ServicoSessaoIF getServicoSessao() {
        return servicoSessao;
    }

    public void setServicoSessao(ServicoSessaoIF servicoSessao) {
        this.servicoSessao = servicoSessao;
    }
    
    
    
}
