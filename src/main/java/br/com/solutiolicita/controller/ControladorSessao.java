package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.servicos.ServicoSessaoIF;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
@Model
public class ControladorSessao {
    
    private Sessao sessao;
    
    @Inject
    private ServicoSessaoIF servicoSessao;
    
    public ControladorSessao(){
    }
    
    @PostConstruct
    public void init(){
        sessao = new Sessao();
    }
}
