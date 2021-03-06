package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.Item;
import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.servicos.ServicoPregaoIF;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Matheus Oliveira
 */
@Named
@ViewScoped
public class ControladorAdicionarItens implements Serializable {

    private Pregao pregao;
    private Item item;
    private ItemPregao itemPregao;

    @Inject
    private ServicoPregaoIF servicoPregao;

    public ControladorAdicionarItens() {
        itemPregao = new ItemPregao();
    }

    public void adicionarItem() {
        if (!verificarItensPregao(item)) {
            itemPregao.setItem(item);
            itemPregao.setPregao(pregao);
            pregao.getItensPregoes().add(itemPregao);
            itemPregao = new ItemPregao();
        } else {
            itemPregao = new ItemPregao();
        }
        Logger.getGlobal().log(Level.INFO, "Adicionando itemPregao {0}", itemPregao);
    }

    private boolean verificarItensPregao(Item item) {
        for (ItemPregao itemP : pregao.getItensPregoes()) {
            if (itemP.getItem().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void removerItem() {
        Logger.getGlobal().log(Level.INFO, "Removendo itemPregao {0}", itemPregao);
        pregao.getItensPregoes().remove(itemPregao);
    }

    public String atualizar() {
        servicoPregao.atualizar(pregao);
        return "/restrito/pregao/pregao.xhtml";
    }

    public Pregao getPregao() {
        return pregao;
    }

    public void setPregao(Pregao pregao) {
        this.pregao = pregao;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemPregao getItemPregao() {
        return itemPregao;
    }

    public void setItemPregao(ItemPregao itemPregao) {
        this.itemPregao = itemPregao;
    }

    public void editandoXlsParaExportar(Object document) {
        servicoPregao.atualizar(pregao);
        Logger.getGlobal().log(Level.INFO, "Iniciando export .XLS {0}", getPregao());
        servicoPregao.criarPlanilhaXLS(document, pregao);
    }

}
