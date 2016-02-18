package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.InstituicaoLicitadora;
import br.com.solutiolicita.servicos.ServicoInstituicaoLicitadoraIF;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
@Model
public class ControladorLicitador {

    private InstituicaoLicitadora licitadora;

    @Inject
    private ServicoInstituicaoLicitadoraIF servicoLicitadora;

    public ControladorLicitador() {
    }

    @PostConstruct
    public void init() {
        licitadora = new InstituicaoLicitadora();
    }

    public String criar() {
        servicoLicitadora.criar(licitadora);
        return "/restrito/licitadores/licitador.xhtml?faces-redirect=true";
    }

    public String editar() {
        servicoLicitadora.atualizar(licitadora);
        return "/restrito/licitadores/licitador.xhtml?faces-redirect=true";
    }

    public String prepararEditar() {
        return "/restrito/licitadores/licitadorEditar.xhtml";
    }

    public void remover() {
        servicoLicitadora.remover(licitadora);
    }

    public List<InstituicaoLicitadora> getLicitadoras() {
        return servicoLicitadora.buscarTodos();
    }

    public InstituicaoLicitadora getLicitadora() {
        return licitadora;
    }

    public void setLicitadora(InstituicaoLicitadora licitadora) {
        this.licitadora = licitadora;
    }

}
