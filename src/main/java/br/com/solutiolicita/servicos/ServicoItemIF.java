package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.Item;


/**
 *
 * @author ricardocaldeira
 */
public interface ServicoItemIF extends ServicoIF<Item> {
    
    public void validarItem(Item item) throws ExcecoesLicita;

}
