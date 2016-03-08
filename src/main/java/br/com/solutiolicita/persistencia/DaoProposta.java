/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.persistencia;

import br.com.solutiolicita.modelos.Proposta;

/**
 *
 * @author Matheus Oliveira
 */
public class DaoProposta extends DaoGenerico<Proposta>{
    
    public DaoProposta(){
        super(Proposta.class);
    }
    
}
