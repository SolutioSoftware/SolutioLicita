package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.modelos.Proposta;
import br.com.solutiolicita.modelos.Sessao;
import java.util.List;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ricardocaldeira
 */
public interface ServicoSessaoIF extends ServicoIF<Sessao> {
    
    public List<Proposta> importarValoresPlanilha(UploadedFile file, Pregao pregao, Sessao sessao, EmpresaLicitante licitante) throws ExcecoesLicita;
    
    public void salvarPropostar(List<Proposta> propostas);
    
    public void buscarPropostas(Sessao sessao ,List<EmpresaLicitante> empresaLicitantes);

    public void validarArquivoXLS(UploadedFile planilhaImport, Pregao pregao, Sessao sessao, EmpresaLicitante licitante) throws ExcecoesLicita;
    
}
