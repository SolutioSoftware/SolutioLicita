package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Lance;
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
    
    public void salvarLance(Lance lance);
    
    public List<Proposta> buscarPropostas(ItemPregao itemPregao);
    
    public List<Lance> buscarLances(ItemPregao itemPregao);
    
    public List<ItemPregao> buscarItensPregao (Pregao Pregao);
    
    public List<Proposta> classificarPropostas(List<Proposta> propostasNaoClassif);
    
    public boolean validarLance(Lance lance, Lance ultimoLance, Proposta melhorProposta);

    public List<Proposta> validarArquivoXLS(UploadedFile planilhaImport, Pregao pregao, Sessao sessao, EmpresaLicitante licitante) throws ExcecoesLicita;
    
}
