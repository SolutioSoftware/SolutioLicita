package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.servicos.ServicoPregaoIF;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

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

    /**
     * Metodos para criacao da planilha que sera exportada.
     * @param document 
     */
}
