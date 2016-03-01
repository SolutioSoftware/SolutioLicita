package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.Proposta;
import br.com.solutiolicita.modelos.Sessao;
import java.util.List;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ricardocaldeira
 */
public interface ServicoSessaoIF extends ServicoIF<Sessao> {
    
    public List<EmpresaLicitante> getEmpresasLicitantes();
    public List<Proposta> getPropostas(Sessao sessao);

    /**
     *
     * @param uploadArquivo
     * @return
     */
    public boolean filtraPlanilha(UploadedFile uploadArquivo);

    public boolean salvarPropostas(Sessao entidade, EmpresaLicitante empresaLicitante);
    
    public void buscarPropostas(Sessao sessao ,List<EmpresaLicitante> empresaLicitantes);

    public void validarArquivoXLS(UploadedFile planilhaImport) throws ExcecoesLicita;
    
}
