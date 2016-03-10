/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.servicos.ServicoSessaoIF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ricardocaldeira
 */
@Named
@ViewScoped
public class ControladorFaseDeLance implements Serializable {

    private Sessao sessao;
    private Pregao pregao;
    private List<ItemPregao> itens;
    private ItemPregao itemPregao;
    private int indiceDoItem;

    @Inject
    private ServicoSessaoIF servicoSessao;

    public ControladorFaseDeLance() {
    }

    @PostConstruct
    public void iniciar() {
        sessao = new Sessao();
        pregao = new Pregao();
        itens = new ArrayList();
        indiceDoItem = 1;
    }

    public String encerrarItem() {
        indiceDoItem++;
        if(indiceDoItem > itens.size()){
            JsfUtil.addErrorMessage("Não há mais itens!");
            return "/restrito/index.xhtml";
        }
        return null;
    }

    public Pregao getPregao() {
        return pregao;
    }

    public void setPregao(Pregao pregao) {
        this.pregao = pregao;
    }

    public void carregarFaseDeLance() {
        if (itens.isEmpty()) {
            itens = this.servicoSessao.carregarFaseDeLance(pregao);
        }
        if (!itens.isEmpty()) {
            Logger.getGlobal().log(Level.INFO, "indice do item: "+indiceDoItem);
            if (itens.size() >= indiceDoItem) {
                itemPregao = itens.get(indiceDoItem - 1);
            } 
        }
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public ItemPregao getItemPregao() {
        carregarFaseDeLance();
        return itemPregao;
    }

    public void setItemPregao(ItemPregao itemPregao) {
        this.itemPregao = itemPregao;
    }

    public int getIndiceDoItem() {
        return indiceDoItem;
    }

}
