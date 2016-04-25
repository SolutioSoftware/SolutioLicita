/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutio.util;

import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Proposta;
import br.com.solutiolicita.util.ClassificadorPropostas;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 *
 * @author WitaloCarlos
 */
public class ClassificarPropostasTest {
    
    public ClassificarPropostasTest() {
    }
    
   List<Proposta> propostas;
    
    @Before
    public void setUp() {
        propostas = new ArrayList<>();
        
        Proposta propostaUm = new Proposta();
      
        propostaUm.setIdItemPregao(new ItemPregao());
        propostaUm.setId(new Long(1));
        propostaUm.setIdLicitante(new EmpresaLicitante());
        propostaUm.setValorUnitario(new BigDecimal("12.5"));
           
        propostas.add(propostaUm);
        
        Proposta propostaDois = new Proposta();
      
        propostaDois.setIdItemPregao(new ItemPregao());
        propostaDois.setId(new Long(1));
        propostaDois.setIdLicitante(new EmpresaLicitante());
        propostaDois.setValorUnitario(new BigDecimal("10.0"));
           
        propostas.add(propostaDois);
        
        Proposta propostaTres = new Proposta();
      
        propostaTres.setIdItemPregao(new ItemPregao());
        propostaTres.setId(new Long(1));
        propostaTres.setIdLicitante(new EmpresaLicitante());
        propostaTres.setValorUnitario(new BigDecimal("9.5"));
           
        propostas.add(propostaTres);
        
        Proposta propostaQuatro = new Proposta();
      
        propostaQuatro.setIdItemPregao(new ItemPregao());
        propostaQuatro.setId(new Long(1));
        propostaQuatro.setIdLicitante(new EmpresaLicitante());
        propostaQuatro.setValorUnitario(new BigDecimal("15.97"));
           
        propostas.add(propostaQuatro);
        
        Proposta propostaCinco = new Proposta();
      
        propostaCinco.setIdItemPregao(new ItemPregao());
        propostaCinco.setId(new Long(1));
        propostaCinco.setIdLicitante(new EmpresaLicitante());
        propostaCinco.setValorUnitario(new BigDecimal("5.0"));
           
        propostas.add(propostaCinco);
        
        Proposta propostaSeis = new Proposta();
      
        propostaSeis.setIdItemPregao(new ItemPregao());
        propostaSeis.setId(new Long(1));
        propostaSeis.setIdLicitante(new EmpresaLicitante());
        propostaSeis.setValorUnitario(new BigDecimal("11.3"));
           
        propostas.add(propostaSeis);
        
        Proposta propostaSete = new Proposta();
      
        propostaSete.setIdItemPregao(new ItemPregao());
        propostaSete.setId(new Long(1));
        propostaSete.setIdLicitante(new EmpresaLicitante());
        propostaSete.setValorUnitario(new BigDecimal("7.0"));
           
        propostas.add(propostaSete);
        
        
        
    }
    
    
    @Test
    public void ordenacaoTest(){
        
       ClassificadorPropostas classificadorPropostas = new ClassificadorPropostas(propostas);
        
        propostas = classificadorPropostas.getPropostasOrdenadasCrescente();              
        
        assertEquals(new BigDecimal("5.0"), propostas.get(0).getValorUnitario());
        assertEquals(new BigDecimal("7.0"), propostas.get(1).getValorUnitario());
        assertEquals(new BigDecimal("9.5"), propostas.get(2).getValorUnitario());
        assertEquals(new BigDecimal("10.0"), propostas.get(3).getValorUnitario());
        assertEquals(new BigDecimal("11.3"), propostas.get(4).getValorUnitario());
        assertEquals(new BigDecimal("12.5"), propostas.get(5).getValorUnitario());
        assertEquals(new BigDecimal("15.97"), propostas.get(6).getValorUnitario());
        
        
    }
    
    
    @Test
    public void preClassificacaoTest(){
        
       ClassificadorPropostas classificadorPropostas = new ClassificadorPropostas(propostas);
        
        propostas = classificadorPropostas.preClassificarPropostas();
        
        assertEquals(new BigDecimal("5.0"), propostas.get(0).getValorUnitario());
        assertEquals(true, propostas.get(0).getClassificada());
        assertEquals(3, propostas.size());       
        
        
    }
}
