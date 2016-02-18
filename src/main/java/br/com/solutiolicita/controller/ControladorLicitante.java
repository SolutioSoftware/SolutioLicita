package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.servicos.ServicoLicitanteIF;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

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
    
    public String criar(){
        servicoLicitante.criar(licitante);
        return "/restrito/licitantes/licitante.xhtml";
    }
    
    public String editar(){
        servicoLicitante.atualizar(licitante);
        return "/restrito/licitantes/licitante.xhtml?faces-redirect=true";
    }
    
    public String prepararEditar(){
        return "/restrito/licitantes/licitanteEditar.xhtml";
    }
    
    public void remover(){
        servicoLicitante.remover(licitante);
    }
    
    public List<EmpresaLicitante> getLicitantes(){
        return servicoLicitante.buscarTodos();
    }

    public EmpresaLicitante getLicitante() {
        return licitante;
    }

    public void setLicitante(EmpresaLicitante licitante) {
        this.licitante = licitante;
    }
    
}
