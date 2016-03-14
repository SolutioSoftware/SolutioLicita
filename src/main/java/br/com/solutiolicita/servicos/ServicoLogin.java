package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.Login;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import br.com.solutiolicita.util.Criptografar;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoLogin implements ServicoLoginIF{
    
    @Inject
    private DaoIF<Login> dao;
    
    public ServicoLogin(){}
    
    private DaoIF<Login> getDao(){
        return dao;
    }

    @Override
    public Login verificarDados(String usuario, String senha) {
        if (usuario != null && senha != null) {
            String senhaCript;
            senhaCript = Criptografar.getInstance().criptografar(senha);
            String[] parametros = {"usuario", "senha"};
            Object[] valores = {usuario, senhaCript};
            List<Login> list = getDao().consultar("Login.buscaPorLogin", parametros, valores);
            if(list.isEmpty()){
                return null;
            }
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void criar(Login entidade) {
    }

    @Override
    public void remover(Login entidade) {
    }

    @Override
    @Transactional
    public void atualizar(Login entidade) {
        String senhaCrip = Criptografar.getInstance().criptografar(entidade.getSenha());
        entidade.setSenha(senhaCrip);
        dao.atualizar(entidade);
    }

    @Override
    public Login buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Login> buscarTodos() {
        return null;
    }

    @Override
    public void verificarNovosValores(Login login, String senhaAtual, String novaSenha, String confirmarSenha) throws ExcecoesLicita {
        String senhaCrip = Criptografar.getInstance().criptografar(senhaAtual);
        if(login.getSenha().equals(senhaCrip)){
            if(!senhaAtual.equals(novaSenha)){
                if(!novaSenha.equals(confirmarSenha)){
                    throw new ExcecoesLicita("ERROR 15 - Novas senhas não conferem!");
                }
            }else{
                throw new ExcecoesLicita("ERROR 14 - Senha atual igual a nova senha!");
            }
        }else{
            throw new ExcecoesLicita("ERROR 13 - Senha atual não confere!");
        }
    }
    
}
