package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.ENUMStatusPregao;
import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import java.util.List;
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
public class ServicoPregao implements ServicoPregaoIF {

    @Inject
    private DaoIF<Pregao> dao;

    @Inject
    private DaoIF<ItemPregao> daoItemPregao;

    public ServicoPregao() {
    }

    @Override
    @Transactional
    public void criar(Pregao entidade) {
        entidade.setStatusPregao(ENUMStatusPregao.ABERTO);
        dao.criar(entidade);
    }

    @Override
    @Transactional
    public void remover(Pregao entidade) {
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(Pregao entidade) {
        dao.atualizar(entidade);
    }

    @Override
    public Pregao buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<Pregao> buscarTodos() {
        return dao.consultar("Pregao.findAll");
    }

    /**
     * Método responsável por gerar a planilha que será exportada para que haja
     * o preenchimento da mesma, e então, volte ao sistema para que ocorra a
     * carga dos valores para que seja possível sua utilização
     *
     * @param documento
     * @param pregao
     */
    @Override
    public void criarPlanilhaXLS(Object documento, Pregao pregao) {
        HSSFWorkbook wb = (HSSFWorkbook) documento;
        HSSFSheet planilha = wb.getSheetAt(0);

        //Move as celulas selecionadas para baixo de acordo com o valor informado
        planilha.shiftRows(planilha.getFirstRowNum(), planilha.getLastRowNum(), 5);

        HSSFRow linha0 = planilha.createRow(0);
        linha0.createCell(0).setCellValue("Instituição Licitadora:");
        planilha.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        linha0.createCell(2).setCellValue(" " + pregao.getInstituicaoLicitadora().getPessoaJuridica().getNomeFantasia());
        planilha.addMergedRegion(new CellRangeAddress(0, 0, 2, 6));

        HSSFRow linha1 = planilha.createRow(1);
        linha1.createCell(0).setCellValue("Numero do Pregao:");
        planilha.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
        linha1.createCell(2).setCellValue(" " + pregao.getNumeroPregao());
        planilha.addMergedRegion(new CellRangeAddress(1, 1, 2, 6));

        HSSFRow linha2 = planilha.createRow(2);
        linha2.createCell(0).setCellValue("Numero do Processo:");
        planilha.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));
        linha2.createCell(2).setCellValue(" " + pregao.getNumeroProcesso());
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

    @Override
    public void validarPregao(Pregao pregao) throws ExcecoesLicita {
        if (pregao == null) {
            throw new ExcecoesLicita("ERROR 12 - Pregao possui campos obrigatórios vazios!");
        } else if (pregao.getNumeroPregao() == null) {
            throw new ExcecoesLicita("ERROR 12 - Pregao possui campos obrigatórios vazios!");
        } else if (pregao.getNumeroProcesso() == null) {
            throw new ExcecoesLicita("ERROR 12 - Pregao possui campos obrigatórios vazios!");
        }
    }
}
