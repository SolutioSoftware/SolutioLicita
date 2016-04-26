package br.com.solutiolicita.persistencia;

import br.com.solutiolicita.excecoes.ExcecoesRunTimeLicita;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Matheus Oliveira
 * @param <T>
 */
public class DaoGenerico<T> implements DaoIF<T>, Serializable {

    @Inject
    private EntityManager entityManager;

    private final Class<T> entidade;

    public DaoGenerico(Class<T> clazz) {
        this.entidade = clazz;
    }

    @Override
    public boolean criar(T entidade) {
        entityManager.persist(entidade);
        return true;
    }

    @Override
    public boolean remover(T entidade) {
        entidade = entityManager.merge(entidade);
        entityManager.remove(entidade);
        return true;
    }

    @Override
    public boolean atualizar(T entidade) {
        entityManager.merge(entidade);
        return true;
    }

    @Override
    public T buscarPorId(Long id) {
        return entityManager.find(this.entidade, id);
    }

    @Override
    public List consultar(String namedQuery, String[] parametros, Object[] valores) {
        Query query = getEntityManager().createNamedQuery(namedQuery, entidade);

        if (parametros != null && valores != null) {
            if (parametros.length == valores.length) {

                for (int i = 0; i < parametros.length; i++) {

                    query.setParameter(parametros[i], valores[i]);

                }

                return query.getResultList();

            } else {
                throw new ExcecoesRunTimeLicita("As listas de parametros e valores não possuem tamanhos iguais.");
            }
        } else {
            throw new ExcecoesRunTimeLicita("As listas de parametros e/ou valores não devem ser nulas.");
        }

    }

    @Override
    public boolean remover(String namedQuery, String[] parametros, Object[] valores) {
        Query query = getEntityManager().createNamedQuery(namedQuery, entidade);

        if (parametros != null && valores != null) {
            if (parametros.length == valores.length) {

                for (int i = 0; i < parametros.length; i++) {

                    query.setParameter(parametros[i], valores[i]);

                }

                query.executeUpdate();
                return true;

            } else {
                throw new ExcecoesRunTimeLicita("As listas de parametros e valores não possuem tamanhos iguais.");
            }
        } else {
            throw new ExcecoesRunTimeLicita("As listas de parametros e/ou valores não devem ser nulas.");
        }

    }

    @Override
    public List consultar(String namedQuery) {
        if (namedQuery != null) {

            Query query = getEntityManager().createNamedQuery(namedQuery, entidade);
            return query.getResultList();

        } else {
            throw new ExcecoesRunTimeLicita("O valor namedQuery não pode ser nulo.");
        }

    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
