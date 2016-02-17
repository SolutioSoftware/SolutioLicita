package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.Item;
import br.com.solutiolicita.servicos.ServicoItemIF;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
@Model
public class ControladorItem {

    private Item item;

    @Inject
    private ServicoItemIF servicoItem;

    public ControladorItem() {
    }

    @PostConstruct
    public void inicializar() {
        item = new Item();
    }

    public void criar() {
        servicoItem.criar(item);
    }

    public void editar() {
        servicoItem.atualizar(item);
    }

    public void remover() {
        servicoItem.remover(item);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
