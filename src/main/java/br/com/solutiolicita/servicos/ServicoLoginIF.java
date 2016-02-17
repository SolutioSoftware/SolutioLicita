package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.Login;


/**
 *
 * @author Matheus Oliveira
 */
public interface ServicoLoginIF extends ServicoIF<Login>{
    
    public boolean verificarDados(String usuario, String senha);
}
