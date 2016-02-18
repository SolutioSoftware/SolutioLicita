package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.modelos.Item;
import br.com.solutiolicita.servicos.ServicoItemIF;
import java.util.List;
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
    
    public String prepararEditar(){
        return "/restrito/item/itemEditar.xhtml";
    }

    public String criar() {
        servicoItem.criar(item);
        return "/restrito/item/itemSalvar.xhtml?faces-redirect=true";
    }

    public String editar() {
        servicoItem.atualizar(item);
        return "/restrito/item/item.xhtml?faces-redirect=true";
        
    }

    public void remover() {
        servicoItem.remover(item);
    }
    
    public List<Item> getItens(){
        return servicoItem.buscarTodos();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
}
