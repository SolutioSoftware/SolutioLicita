package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.servicos.ServicoPregaoIF;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Witalo Carlos
 */
@Named
@ViewScoped
public class ControladorAdicionarLicitante implements Serializable {

    private Pregao pregao;
    private EmpresaLicitante empresaLicitante;

    @Inject
    private ServicoPregaoIF servicoPregao;

    public ControladorAdicionarLicitante() {
        empresaLicitante = new EmpresaLicitante();
    }

    public void adicionarEmpresa() {
        if (!getPregao().getEmpresasLicitantes().contains(getEmpresaLicitante())) {
            pregao.getEmpresasLicitantes().add(getEmpresaLicitante());
            setEmpresaLicitante(new EmpresaLicitante());
        } else {
            setEmpresaLicitante(new EmpresaLicitante());
        }
        
    }

    public void removerEmpresa() {
        pregao.getEmpresasLicitantes().remove(getEmpresaLicitante());
    }

    public String atualizar() {
        servicoPregao.atualizar(pregao);
        return "/restrito/pregao/pregao.xhtml";
    }

    public Pregao getPregao() {
        return pregao;
    }

    public void setPregao(Pregao pregao) {
        this.pregao = pregao;
    }

    public EmpresaLicitante getEmpresaLicitante() {
        return empresaLicitante;
    }

    public void setEmpresaLicitante(EmpresaLicitante empresaLicitante) {
        this.empresaLicitante = empresaLicitante;
    }

    public ServicoPregaoIF getServicoPregao() {
        return servicoPregao;
    }

    public void setServicoPregao(ServicoPregaoIF servicoPregao) {
        this.servicoPregao = servicoPregao;
    }

    

}
