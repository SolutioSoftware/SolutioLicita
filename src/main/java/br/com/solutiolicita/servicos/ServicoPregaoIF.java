package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.Pregao;

/**
 *
 * @author ricardocaldeira
 */
public interface ServicoPregaoIF extends ServicoIF<Pregao> {
    
    public void criarPlanilhaXLS(Object Documento, Pregao pregao);
    
    public void validarPregao(Pregao pregao) throws ExcecoesLicita;
    
}
