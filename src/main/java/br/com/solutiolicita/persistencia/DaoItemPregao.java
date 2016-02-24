/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.persistencia;

import br.com.solutiolicita.modelos.ItemPregao;

/**
 *
 * @author Matheus Oliveira
 */
public class DaoItemPregao extends DaoGenerico<ItemPregao>{
    
    public DaoItemPregao(){
        super(ItemPregao.class);
    }
    
    @Override
    public boolean remover(ItemPregao itemPregao){
        getEntityManager().remove(itemPregao);
        return true;
    }
}
