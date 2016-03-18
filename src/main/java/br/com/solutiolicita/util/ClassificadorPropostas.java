/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.util;

import br.com.solutiolicita.modelos.Proposta;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author WitaloCarlos
 */
public class ClassificadorPropostas {
    
    List<Proposta> propostas;

    public ClassificadorPropostas(List<Proposta> propostas) {
        this.propostas = propostas;
    }
    
      
    
    public List<Proposta> preClassificarPropostas(){
        
        Proposta menorProposta = getMenorProposta();
        List<Proposta> preClassificadas = new ArrayList<>();
        
        for (Proposta propostaAux : getPropostasOrdenadasCrescente()){
            if (propostaAux.getValorUnitario().doubleValue() <= (menorProposta.getValorUnitario().doubleValue() * 1.1)){
                propostaAux.setClassificada(true);
                preClassificadas.add(propostaAux);
            }
                                      
        }    
        
        return classificarPropostas(preClassificadas);
        
    }
    
    public List<Proposta> classificarPropostasEmpresasPequenoPorte(List<Proposta> propostas){
        
        //TODO verificar regra e implementar funcionalidade.
        
        return null;
        
    }
    
    private List<Proposta> classificarPropostas(List<Proposta> propostas){
        Collections.sort(propostas, Classificador.getInstancia());
        return propostas;
        
    }
    
    
    public Proposta getMenorProposta() throws IndexOutOfBoundsException{
        return getPropostasOrdenadasCrescente().get(0);
    }
    
    
    public List<Proposta> getPropostasOrdenadasCrescente() {
        return classificarPropostas(getPropostas());
    }
    
    public List<Proposta> getPropostasOrdenadasDecrescente() {
        
        List<Proposta> propostasDesc = classificarPropostas(getPropostas());
        Collections.reverse(propostasDesc);
        
        return propostasDesc;
    }

    public List<Proposta> getPropostas() {
       
        if (propostas == null){
            propostas = new ArrayList<>();
        }        
        return propostas;
    }

    public void setPropostas(List<Proposta> propostas) {
        this.propostas = propostas;
    }
    
    
    
    
    
}
