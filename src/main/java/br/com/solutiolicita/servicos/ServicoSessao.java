package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.Proposta;
import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import java.util.List;
import javax.inject.Inject;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoSessao implements ServicoSessaoIF{
    
    @Inject
    private DaoIF<Sessao> dao;
    
    public ServicoSessao(){
    }

    @Override
    public List<EmpresaLicitante> getEmpresasLicitantes() {
        return null;
    }

    @Override
    public List<Proposta> getPropostas(Sessao sessao) {
        return null;
    }

    @Override
    public boolean filtraPlanilha(UploadedFile uploadArquivo) {
        return true;
    }

    @Override
    public boolean salvarPropostas(Sessao entidade, EmpresaLicitante empresaLicitante) {
        return true;
    }

    @Override
    public void buscarPropostas(Sessao sessao, List<EmpresaLicitante> empresaLicitantes) {
    }

    @Override
    @Transactional
    public void criar(Sessao entidade) {
        dao.criar(entidade);
    }

    @Override
    @Transactional
    public void remover(Sessao entidade) {
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(Sessao entidade) {
        dao.atualizar(entidade);
    }

    @Override
    public Sessao buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<Sessao> buscarTodos() {
        List sessoes = dao.consultar("Sessao.findAll");
        return sessoes;
    }
    
}
