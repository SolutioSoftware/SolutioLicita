package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.MembroApoio;
import br.com.solutiolicita.servicos.ServicoMembroApoioIF;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
@Model
public class ControladorMembroApoio {
    
    private MembroApoio membroApoio;
    
    @Inject
    private ServicoMembroApoioIF servicoMembroApoio;
    
    public ControladorMembroApoio(){
    }
    
    @PostConstruct
    public void init(){
        membroApoio = new MembroApoio();
    }
}
