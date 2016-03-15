/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.util;

/**
 * Interface que declara que a classe tem um campo que 
 * servirá de paramêtro de ordenação.
 * @author WitaloCarlos
 * @param <T> Tipo do parâmetro de ordenacao, precisa ser um tipo numerico.
 */
public interface Ordenavel<T extends Number> {
    
    /**
     * 
     * @return 
     */
    T parametroDeOrdenacao();
    
}
