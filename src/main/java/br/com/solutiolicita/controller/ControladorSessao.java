package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.servicos.ServicoSessaoIF;
import java.util.Date;
import java.util.List;
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
    
    public String criar(){
        servicoSessao.criar(sessao);
        return "/restrito/sessao/sessao.xhtml";
    }
    
    public String prepararEditar(){
        return "/restrito/sessao/sessaoEditar.xhtml";
    }
    
    public String atualizar(){
        servicoSessao.atualizar(sessao);
        return "/restrito/sessao/sessao.xhtml?faces-redirect=true";
    }
    
    public void remover(){
        servicoSessao.remover(sessao);
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
    
    public List<Sessao> getSessoes(){
        return servicoSessao.buscarTodos();
    }
    
    public Date getDataAtual(){
        return new Date();
    }
    
}
