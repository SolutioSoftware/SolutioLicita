package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.servicos.ServicoPregaoIF;
import java.util.List;
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

    public ControladorPregao() {
    }

    @PostConstruct
    public void init() {
        pregao = new Pregao();
    }

    
    /*
        Metodos CRUD
    */
    public String criar() {
        servicoPregao.criar(pregao);
        return "/restrito/pregao/pregao.xhtml?faces-redirect=true";
    }

    public String editar() {
        servicoPregao.atualizar(pregao);
        return "/restrito/pregao/pregao.xhtml?faces-redirect=true";
    }

    public String prepararEditar() {
        return "/restrito/pregao/pregaoEditar.xhtml";
    }

    public void remover() {
        servicoPregao.remover(pregao);
    }
    
    /*
        Metodos para lógicas de negócio
    */
    public String prepararAdicionarItens(){
        return "/restrito/pregao/pregaoAdicionarItens.xhtml?faces-redirect=true";
    }
    

    public List<Pregao> getPregoes() {
        return servicoPregao.buscarTodos();
    }
    

    public Pregao getPregao() {
        return pregao;
    }

    public void setPregao(Pregao pregao) {
        this.pregao = pregao;
    }

}
