package br.com.solutiolicita.servicos;

import java.util.List;

/**
 *
 * @author Matheus Oliveira
 */
public interface ServicoIF<T>{
    
    public void criar(T entidade);
    
    public void remover(T entidade);
    
    public void atualizar(T entidade);
    
    public T buscarPorId(Long id);
    
     public List<T> buscarTodos();
    
}
