package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.Login;
import br.com.solutiolicita.persistencia.DaoGenerico;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.util.Criptografar;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoLogin implements ServicoLoginIF{
    
    private final DaoIF<Login> dao = new DaoGenerico(Login.class);
    
    @Inject
    private EntityManager entityManager;
    
    public ServicoLogin(){}
    
    private DaoIF<Login> getDao(){
        return dao;
    }

    @Override
    public boolean verificarDados(String usuario, String senha) {
        if (usuario != null && senha != null) {
            String senhaCript;
            senhaCript = Criptografar.getInstance().criptografar(senha);
            String[] parametros = {"usuario", "senha"};
            Object[] valores = {usuario, senhaCript};
            List<Login> list = getDao().consultar("Login.buscaPorLogin", parametros, valores);
            return !list.isEmpty();
        } else {
            return false;
        }
    }

    @Override
    public void criar(Login entidade) {
    }

    @Override
    public void remover(Login entidade) {
    }

    @Override
    public void atualizar(Login entidade) {
    }

    @Override
    public Login buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Login> buscarTodos() {
        return null;
    }
    
}
