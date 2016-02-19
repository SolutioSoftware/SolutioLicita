/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.persistencia;

import br.com.solutiolicita.modelos.EmpresaLicitante;

/**
 *
 * @author Matheus Oliveira
 */
public class DaoEmpresaLicitante extends DaoGenerico<EmpresaLicitante>{
    
    public DaoEmpresaLicitante(){
        super(EmpresaLicitante.class);
    }
}
