package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.MembroApoio;

/**
 *
 * @author Matheus Oliveira
 */
public interface ServicoMembroApoioIF extends ServicoIF<MembroApoio>{
    
    public void validarMembroApoio(MembroApoio apoio) throws ExcecoesLicita;
}
