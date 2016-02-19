package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.Item;
import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Pregao;
import static br.com.solutiolicita.modelos.Pregao_.itensPregoes;
import br.com.solutiolicita.servicos.ServicoPregaoIF;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    private ItemPregao itemPregao;
    private Item item;
    private Set<ItemPregao> itensPregao;

    @Inject
    private ServicoPregaoIF servicoPregao;

    public ControladorPregao() {
    }

    @PostConstruct
    public void init() {
        pregao = new Pregao();
        item = new Item();
        itemPregao = new ItemPregao();
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
        return "/restrito/pregao/pregaoAdicionarItens.xhtml";
    }
    
    public String adicionarItem(){
        //TO-DO
        itemPregao.setItem(item);
        itemPregao.setPregao(pregao);
        itensPregao.add(itemPregao);
        return "/restrito/pregao/pregaoAdicionarItens.xhtml";
    }
    
    public String removerItem(){
        //TO-DO
        itensPregao.remove(itemPregao);
        return "/restrito/pregao/pregaoAdicionarItens.xhtml";
    }

    public List<Pregao> getPregoes() {
        return servicoPregao.buscarTodos();
    }
    
    public Set<ItemPregao> getItensPregao(){
        //TO-DO
        return servicoPregao.buscarItensPregoes(pregao);
    }

    public Pregao getPregao() {
        return pregao;
    }

    public void setPregao(Pregao pregao) {
        this.pregao = pregao;
    }

    public ItemPregao getItemPregao() {
        return itemPregao;
    }

    public void setItemPregao(ItemPregao itemPregao) {
        this.itemPregao = itemPregao;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    
    
    /**
     * Metodos para criacao da planilha que sera exportada.
     * @param document 
     */
    public void editandoXlsParaExportar(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet planilha = wb.getSheetAt(0);

        //Move as celulas selecionadas para baixo de acordo com o valor informado
        planilha.shiftRows(planilha.getFirstRowNum(), planilha.getLastRowNum(), 5);

        HSSFRow linha0 = planilha.createRow(0);
        linha0.createCell(0).setCellValue("Instituição Licitadora:");
        planilha.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        linha0.createCell(2).setCellValue(" " + getPregao().getInstituicaoLicitadora().getPessoaJuridica().getNomeFantasia());
        planilha.addMergedRegion(new CellRangeAddress(0, 0, 2, 6));

        HSSFRow linha1 = planilha.createRow(1);
        linha1.createCell(0).setCellValue("Numero do Pregao:");
        planilha.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
        linha1.createCell(2).setCellValue(" " + getPregao().getNumeroPregao());
        planilha.addMergedRegion(new CellRangeAddress(1, 1, 2, 6));

        HSSFRow linha2 = planilha.createRow(2);
        linha2.createCell(0).setCellValue("Numero do Processo:");
        planilha.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));
        linha2.createCell(2).setCellValue(" " + getPregao().getNumeroProcesso());
        planilha.addMergedRegion(new CellRangeAddress(2, 2, 2, 6));

        HSSFRow linha3 = planilha.createRow(3);
        linha3.createCell(0).setCellValue("Empresa Licitante:");
        planilha.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));
        linha3.createCell(2).setCellValue("Preencha com o nome de sua Empresa");
        planilha.addMergedRegion(new CellRangeAddress(3, 3, 2, 6));

        HSSFRow linha4 = planilha.createRow(4);

        //Nova coluna para a empresas adicionarem seus valores
        HSSFRow linha5 = planilha.getRow(5);
        HSSFCell celula5 = linha5.createCell(5);
        celula5.setCellValue("Valor do Licitante");

        //for para ajustar automaticamente o tamnho das colunas
        for (int i = 0; i < 6; i++) {
            planilha.autoSizeColumn(i);
        }

        //Cor da linha de titulos da tabela
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        for (int i = 0; i < linha5.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = linha5.getCell(i);

            cell.setCellStyle(cellStyle);
        }

        
        CellStyle unlockedCellStyle = wb.createCellStyle();
        unlockedCellStyle.setLocked(false);
        
        HSSFCell celula2 = linha3.getCell(2);
        celula2.setCellStyle(unlockedCellStyle);
        
    }

}
