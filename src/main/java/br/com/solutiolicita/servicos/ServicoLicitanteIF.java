package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.EmpresaLicitante;

/**
 *
 * @author ricardocaldeira
 */
public interface ServicoLicitanteIF extends ServicoIF<EmpresaLicitante> {
    
    public void validarLicitante(EmpresaLicitante licitante) throws ExcecoesLicita;
    
}
