package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.Pregoeiro;
import br.com.solutiolicita.servicos.ServicoPregoeiroIF;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
@Model
public class ControladorPregoeiro {
    
    private Pregoeiro pregoeiro;
    
    @Inject
    private ServicoPregoeiroIF servicoPregoeiro;
    
    public ControladorPregoeiro(){
    }
    
    @PostConstruct
    public void init(){
        pregoeiro = new Pregoeiro();
    }
}
