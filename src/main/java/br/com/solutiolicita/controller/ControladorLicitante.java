package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.servicos.ServicoLicitanteIF;
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

    public EmpresaLicitante getLicitante() {
        return licitante;
    }

    public void setLicitante(EmpresaLicitante licitante) {
        this.licitante = licitante;
    }
    
}
