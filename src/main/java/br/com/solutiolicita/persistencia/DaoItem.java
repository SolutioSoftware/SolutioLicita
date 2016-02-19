/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.persistencia;

import br.com.solutiolicita.modelos.Item;

/**
 *
 * @author Matheus Oliveira
 */
public class DaoItem extends DaoGenerico<Item> {
    
    public DaoItem(){
        super(Item.class);
    }
    
}
