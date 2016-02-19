package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.Pregoeiro;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoPregoeiro implements ServicoPregoeiroIF{
    
    @Inject
    private DaoIF<Pregoeiro> dao;
    
    public ServicoPregoeiro(){
    }

    @Override
    @Transactional
    public void criar(Pregoeiro entidade) {
        dao.criar(entidade);
    }

    @Override
    @Transactional
    public void remover(Pregoeiro entidade) {
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(Pregoeiro entidade) {
        dao.atualizar(entidade);
    }

    @Override
    public Pregoeiro buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<Pregoeiro> buscarTodos() {
        return dao.consultar("Pregoeiro.findAll");
    }
    
}
