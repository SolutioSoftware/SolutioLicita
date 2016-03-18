/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.controller.util;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.Proposta;
import java.util.List;

/**
 *
 * @author Matheus Oliveira
 */
public class IteradorRodada {

    private int indiceDoOfertante;
    private final int LIMITE_PROPOSTAS = 1;

    public IteradorRodada() {
        this.indiceDoOfertante = 0;
    }

    public EmpresaLicitante proximo(List<Proposta> propostas) {
        indiceDoOfertante++;
        if (indiceDoOfertante >= propostas.size()) {
            indiceDoOfertante = 0;
            return propostas.get(indiceDoOfertante).getIdLicitante();
        } else {
            return propostas.get(indiceDoOfertante).getIdLicitante();
        }
    }

    public void reiniciarIterador() {
        indiceDoOfertante = 0;
    }

    public List<Proposta> remocao(List<Proposta> propostas)throws ExcecoesLicita {
        if (propostas.size() > LIMITE_PROPOSTAS) {
            propostas.remove(indiceDoOfertante);
            indiceDoOfertante--;
            return propostas;
        }else{
            throw new ExcecoesLicita("Não é possível mais declinar, pois, só há " + LIMITE_PROPOSTAS + " Licitante!");
        }
    }

    public int getIndiceDoOfertante() {
        return indiceDoOfertante;
    }

    public void setIndiceDoOfertante(int indiceDoOfertante) {
        this.indiceDoOfertante = indiceDoOfertante;
    }

}
