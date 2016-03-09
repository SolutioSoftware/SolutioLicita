package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.InstituicaoLicitadora;

/**
 *
 * @author ricardocaldeira
 */
public interface ServicoInstituicaoLicitadoraIF extends ServicoIF<InstituicaoLicitadora>{
 
    public void validarLicitador(InstituicaoLicitadora licitadora)throws ExcecoesLicita;
}
