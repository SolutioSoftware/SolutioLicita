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

    private void converterArquivoXLStoHSSF(UploadedFile planilhaImport) throws ExcecoesLicita {
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(planilhaImport.getInputstream());
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
        if (wb != null) {
            HSSFSheet planilhaXLS = wb.getSheetAt(wb.getActiveSheetIndex());
            validarCamposArquivo(planilhaXLS);
        }else{
            throw new ExcecoesLicita("ERROR 03 - Não foi possível carregar os dados da planilha.");
        }

    }

    private void validarCamposArquivo(HSSFSheet planilhaXLS) {
        //TO-DO
        Logger.getGlobal().log(Level.INFO, "ainda não implementado");
        
    }
    //<<Fim - (MVP)>>
}
