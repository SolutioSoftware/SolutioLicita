package br.com.solutiolicita.servicos;

import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.persistencia.DaoIF;
import br.com.solutiolicita.persistencia.util.Transactional;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Matheus Oliveira
 */
public class ServicoLicitante implements ServicoLicitanteIF {

    @Inject
    private DaoIF<EmpresaLicitante> dao;

    public ServicoLicitante() {
    }

    @Override
    @Transactional
    public void criar(EmpresaLicitante entidade) {
        dao.criar(entidade);
    }

    @Override
    @Transactional
    public void remover(EmpresaLicitante entidade) {
        dao.remover(entidade);
    }

    @Override
    @Transactional
    public void atualizar(EmpresaLicitante entidade) {
        dao.atualizar(entidade);
    }

    @Override
    public EmpresaLicitante buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<EmpresaLicitante> buscarTodos() {
        return dao.consultar("EmpresaLicitante.findAll");
    }

    @Override
    public void validarLicitante(EmpresaLicitante licitante) throws ExcecoesLicita {
        if(licitante == null){
            throw new ExcecoesLicita("ERROR 11 - Empresa Licitante possui campos obrigatórios vazios!");
        }else if(licitante.getPessoaJuridica().getCnpj() == null){
            throw new ExcecoesLicita("ERROR 11 - Empresa Licitante possui campos obrigatórios vazios!");
        }else if(licitante.getPessoaJuridica().getRazaoSocial() == null){
            throw new ExcecoesLicita("ERROR 11 - Empresa Licitante possui campos obrigatórios vazios!");
        }else if(licitante.getTipoEmpresa() == null){
            throw new ExcecoesLicita("ERROR 11 - Empresa Licitante possui campos obrigatórios vazios!");
        }
            
    }
}
