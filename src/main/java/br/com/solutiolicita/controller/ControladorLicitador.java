package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.InstituicaoLicitadora;
import br.com.solutiolicita.servicos.ServicoInstituicaoLicitadoraIF;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */

@Model
public class ControladorLicitador {
    
    private InstituicaoLicitadora licitadora;
    
    @Inject
    private ServicoInstituicaoLicitadoraIF servicoLicitadora;
    
    public ControladorLicitador(){
    }
    
    @PostConstruct
    public void init(){
        licitadora = new InstituicaoLicitadora();
    }
    
    
}
