package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.Proposta;
import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoSessao implements ServicoSessaoIF {

    @Inject
    private DaoIF<Sessao> dao;

    public ServicoSessao() {
    }

    @Override
    public List<EmpresaLicitante> getEmpresasLicitantes() {
        return null;
    }

    @Override
    public List<Proposta> getPropostas(Sessao sessao) {
        return null;
    }

    @Override
    public boolean filtraPlanilha(UploadedFile uploadArquivo) {
        return true;
    }

    @Override
    public boolean salvarPropostas(Sessao entidade, EmpresaLicitante empresaLicitante) {
        return true;
    }

    @Override
    public void buscarPropostas(Sessao sessao, List<EmpresaLicitante> empresaLicitantes) {
    }

    @Override
    @Transactional
    public void criar(Sessao entidade) {
        dao.criar(entidade);
    }

    @Override
    @Transactional
    public void remover(Sessao entidade) {
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(Sessao entidade) {
        dao.atualizar(entidade);
    }

    @Override
    public Sessao buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<Sessao> buscarTodos() {
        List sessoes = dao.consultar("Sessao.findAll");
        return sessoes;
    }

    // <<Métodos para validação das Planilhas (MVP) >>
    /**
     * Verifica e valida ser a planilha e os valores contidos nela correspondem
     * corretamente aos dados da pregão e não foi alterada.
     *
     * @param planilhaImport
     * @throws ExcecoesLicita
     */
    @Override
    public void validarArquivoXLS(UploadedFile planilhaImport) throws ExcecoesLicita {
        if (planilhaImport == null) {
            throw new ExcecoesLicita("ERROR 01 - Nenhum Arquivo Localizado.");
        } else if (!planilhaImport.getFileName().contains(".xls")) {
            throw new ExcecoesLicita("ERROR 02 - Este Arquivo não é do tipo .XLS.");
        } else {
            converterArquivoXLStoHSSF(planilhaImport);
        }

    }

    /**
     * Realiza a conversao de uma planilha XLS, para um objeto do tipo
     * HSSFWorkbook para que entao possa ser realizado operacoes.
     *
     * @param planilhaImport
     * @throws ExcecoesLicita
     */
    private void converterArquivoXLStoHSSF(UploadedFile planilhaImport) throws ExcecoesLicita {
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(planilhaImport.getInputstream());
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
        if (wb != null) {
            HSSFSheet planilhaXLS = wb.getSheetAt(wb.getActiveSheetIndex());
            validarIdentificadores(planilhaXLS);
        } else {
            throw new ExcecoesLicita("ERROR 03 - Não foi possível carregar os dados da planilha.");
        }

    }

    private void validarIdentificadores(HSSFSheet planilhaXLS) throws ExcecoesLicita {
        /*
        *   Numero de indices que serao necessarios para verificar
        *   se a planilha e realmente a que foi gerada pelo sistema,
        *   para que nao haja a adulteracao da mesma.
         */
        int indValidadores = 3;
        //Valores existentes no cabecalho da planilha que devem existir na planilhaXLS
        String[] identificadores = {"Instituição Licitadora", "Numero do Pregao", "Numero do Processo"};

        for (int i = 0; i < indValidadores; i++) {
            HSSFRow linha = planilhaXLS.getRow(i);
            Logger.getGlobal().log(Level.INFO, linha.toString());
            if (!linha.getCell(0).getStringCellValue().contains(identificadores[i])) {
                throw new ExcecoesLicita("ERROR 04 - A Planilha inserida possui alguma IRREGULARIDADE!");
            }
        }
        validarAtributosPlanilha(planilhaXLS);
    }

    /**
     * Verifica se os atributos que deveriam esta contidos na planilha existem e
     * estao nos lugares corretos
     *
     * @param planilhaXLS
     * @throws ExcecoesLicita
     */
    private void validarAtributosPlanilha(HSSFSheet planilhaXLS) throws ExcecoesLicita {
        // indice da linha, da planilha, que os atributos estao contidos.
        int indLinhaAttTabela = 5;
        // Quantidade de atributos existentes na planilha.
        int quantAtt = 6;
        // Atributos existentes na planilha.
        String[] atributos = {"Código do Item", "Nome do Item", "Unidade", "Quantidade", "Valor de Referência", "Valor do Licitante"};

        //Linha a ser verificada sobre os atributos existentes ou não.
        HSSFRow linha = planilhaXLS.getRow(indLinhaAttTabela);

        for (int i = 0; i < quantAtt; i++) {
            Logger.getGlobal().log(Level.INFO, linha.getCell(i).getStringCellValue());
            
            if (!linha.getCell(i).getStringCellValue().contains(atributos[i])) {
                throw new ExcecoesLicita("ERROR 04 - A Planilha inserida possui alguma IRREGULARIDADE!");
            }
        }

    }
    //<<Fim - (MVP)>>
}
