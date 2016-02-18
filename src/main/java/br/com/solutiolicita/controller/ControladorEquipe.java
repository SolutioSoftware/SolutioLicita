/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.MembroApoio;
import br.com.solutiolicita.modelos.Pregoeiro;
import br.com.solutiolicita.servicos.ServicoMembroApoioIF;
import br.com.solutiolicita.servicos.ServicoPregoeiroIF;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
@Model
public class ControladorEquipe {

    private MembroApoio membroApoio;
    private Pregoeiro pregoeiro;
    @Inject
    private ServicoPregoeiroIF servicoPregoeiro;
    @Inject
    private ServicoMembroApoioIF servicoMembroApoio;

    public ControladorEquipe() {
    }

    @PostConstruct
    public void init() {
        membroApoio = new MembroApoio();
        pregoeiro = new Pregoeiro();
    }

    //Métodos para o Membro de Apoio
    
    public String prepararEditarMembroApoio(){
        return "/restrito/equipe/equipeEditarMembroApoio.xhtml";
    }
    
    public String criarMembroApoio() {
        servicoMembroApoio.criar(membroApoio);
        return "/restrito/equipe/equipe.xhtml";
    }

    public String editarMembroApoio() {
        servicoMembroApoio.atualizar(membroApoio);
        return "/restrito/equipe/equipe.xhtml?faces-redirect=true";
    }

    public void removerMembroApoio() {
        servicoMembroApoio.remover(membroApoio);
    }

    public List<MembroApoio> getMembrosApoio() {
        return servicoMembroApoio.buscarTodos();
    }

    //Metodos para o Pregoeiro
    public String criarPregoeiro() {
        servicoPregoeiro.criar(pregoeiro);
        return "/restrito/equipe/equipe.xhtml";
    }
    
    public String editarPregoeiro(){
        servicoPregoeiro.atualizar(pregoeiro);
        return "/restrito/equipe/equipe.xhtml?faces-redirect=true";
    }
    
    public void removerPregoeiro(){
        servicoPregoeiro.remover(pregoeiro);
    }
    

    public MembroApoio getMembroApoio() {
        return membroApoio;
    }

    public void setMembroApoio(MembroApoio membroApoio) {
        this.membroApoio = membroApoio;
    }

    public Pregoeiro getPregoeiro() {
        return pregoeiro;
    }

    public void setPregoeiro(Pregoeiro pregoeiro) {
        this.pregoeiro = pregoeiro;
    }

}
