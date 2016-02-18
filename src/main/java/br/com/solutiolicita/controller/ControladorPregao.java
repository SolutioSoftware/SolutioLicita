package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.servicos.ServicoPregaoIF;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
@Model
public class ControladorPregao {
    
    private Pregao pregao;
    
    @Inject
    private ServicoPregaoIF servicoPregao;
    
    public ControladorPregao(){
    }
    
    @PostConstruct
    public void init(){
        pregao = new Pregao();
    }

    public Pregao getPregao() {
        return pregao;
    }

    public void setPregao(Pregao pregao) {
        this.pregao = pregao;
    }
    
}
