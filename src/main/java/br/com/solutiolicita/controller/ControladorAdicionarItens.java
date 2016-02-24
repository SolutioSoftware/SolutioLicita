/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.controller;

import br.com.solutiolicita.modelos.Item;
import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.servicos.ServicoPregaoIF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
@Named
@SessionScoped
public class ControladorAdicionarItens implements Serializable {

    private Pregao pregao;
    private Item item;
    private ItemPregao itemPregao;
    private List<ItemPregao> itensPregao;
    private Pregao pregaoAnt;
    
    @Inject
    private transient ServicoPregaoIF servicoPregao;

    public ControladorAdicionarItens() {
    }

    @PostConstruct
    public void inicializar() {
        pregao = new Pregao();
        item = new Item();
        itemPregao = new ItemPregao();
        itensPregao = new ArrayList<>();
    }

    public void adicionarItem() {
        if (!verificarItensPregao(item)) {
            itemPregao.setItem(item);
            itemPregao.setPregao(pregao);
            itensPregao.add(itemPregao);
            itemPregao = new ItemPregao();
        } else {
            itemPregao = new ItemPregao();
        }
        Logger.getGlobal().log(Level.INFO, "Adicionando itemPregao {0}", itemPregao);
    }

    private boolean verificarItensPregao(Item item) {
        for (ItemPregao itemP : itensPregao) {
            if (itemP.getItem().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void removerItem() {
        Logger.getGlobal().log(Level.INFO, "Removendo itemPregao {0}", itemPregao);
        itensPregao.remove(itemPregao);
    }

    public String atualizar() {
        Set<ItemPregao> itens = new HashSet<>(itensPregao);
        pregao.setItensPregoes(itens);
        servicoPregao.atualizar(pregao);
        limparDados();
        return "/restrito/pregao/pregao.xhtml";
    }

    public Pregao getPregao() {
        return pregao;
    }

    public void setPregao(Pregao pregao) {
        this.pregao = pregao;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemPregao getItemPregao() {
        return itemPregao;
    }

    public void setItemPregao(ItemPregao itemPregao) {
        this.itemPregao = itemPregao;
    }

    /**
     * Retorna a lista de ItemPregao que será exibida na view
     * 1 if - Se o pregaoAnt (Pregao Anterior) estiver sendo null,
     * isto implica dizer que o bean foi instanciado pela sua primeira vez
     * e deve realizar a consulta ao banco para retornar a lista de ItemPregao
     * do Pregao que é posto como parâmetro
     * 
     * 2 if- Ele verificar se o pregaoAnt é igual ao Atual, para, que possa manter
     * a mesma lista de ItemPregao e ocorra sua atualização e adição de novos valores
     * 
     * 3 if- Caso o pregaoAnt seja diferente do atual, isto implica dizer que uma
     * nova consulta ao banco deve ser realizada, para que possa exibir os valores
     * correto da lista de ItemPregao do Pregao.
     * @return itensPregao
     */
    public List<ItemPregao> getItensPregao() {
        if(pregaoAnt == null){
            pregaoAnt = pregao;
            itensPregao = servicoPregao.buscarItensPregoes(pregao);
            return itensPregao;
        }else if (pregaoAnt.equals(pregao)){
            return itensPregao;
        }else{
            itensPregao = servicoPregao.buscarItensPregoes(pregao);
            pregaoAnt = pregao;
            return itensPregao;
        }
        
    }

    public void setItensPregao(ArrayList<ItemPregao> itensPregao) {
        this.itensPregao = itensPregao;
    }

    private void limparDados() {
        pregao = new Pregao();
        item = new Item();
        itemPregao = new ItemPregao();
        itensPregao = new ArrayList<>();
        pregaoAnt = null;
    }

    /**
     * Método responsável por gerar a planilha que será exportada para que haja
     * o preenchimento da mesma, e então, volte ao sistema para que ocorra a
     * carga dos valores para que seja possível sua utilização
     *
     * @param document
     */
    public void editandoXlsParaExportar(Object document) {
        Logger.getGlobal().log(Level.INFO, "Iniciando export .XLS {0}", getPregao());
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
