package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.Login;


/**
 *
 * @author Matheus Oliveira
 */
public interface ServicoLoginIF extends ServicoIF<Login>{
    
    public Login verificarDados(String usuario, String senha);
    
    public void verificarNovosValores(Login login,String senhaAtual, String novaSenha, String confirmarSenha) throws ExcecoesLicita;
    
}
