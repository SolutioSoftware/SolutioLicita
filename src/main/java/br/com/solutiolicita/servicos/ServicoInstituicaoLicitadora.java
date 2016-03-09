package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.InstituicaoLicitadora;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.RollbackException;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoInstituicaoLicitadora implements ServicoInstituicaoLicitadoraIF {

    @Inject
    private DaoIF<InstituicaoLicitadora> dao;

    public ServicoInstituicaoLicitadora() {
    }

    @Override
    @Transactional
    public void criar(InstituicaoLicitadora entidade) {
        dao.criar(entidade);
    }

    @Override
    @Transactional
    public void remover(InstituicaoLicitadora entidade) {
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(InstituicaoLicitadora entidade) {
        dao.atualizar(entidade);
    }

    @Override
    public InstituicaoLicitadora buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<InstituicaoLicitadora> buscarTodos() {
        return dao.consultar("InstituicaoLicitadora.findAll");
    }

    //Metodos para validacoes;
    @Override
    public void validarLicitador(InstituicaoLicitadora licitadora) throws ExcecoesLicita {
        if (licitadora.getPessoaJuridica().getCnpj() == null) {
            throw new ExcecoesLicita("ERROR 08 - Instituição Licitadora Possui Campos Obrigatórios Vazios");
        } else if (licitadora.getPessoaJuridica().getNomeFantasia() == null) {
            throw new ExcecoesLicita("ERROR 08 - Instituição Licitadora Possui Campos Obrigatórios Vazios");
        } else if (licitadora.getPessoaJuridica().getRazaoSocial() == null) {
            throw new ExcecoesLicita("ERROR 08 - Instituição Licitadora Possui Campos Obrigatórios Vazios");
        }
    }
}
