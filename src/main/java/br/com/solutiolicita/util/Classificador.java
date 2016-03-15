/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.util;

import java.util.List;

/**
 *Implementação do algoritmo HeapSort para classificação
 * de objetos do tipo Ordenavel
 * @author WitaloCarlos
 * @param <T> Classe do tipo da lista a ser ordenada.
 */
public class Classificador<T extends Ordenavel<?>> {
    
    private static final int ZERO = 0;
    private static final int UM = 1;
    private static final int DOIS = 2;

    
    private static Classificador instancia = null;
  
    private Classificador() {
    }

    public static Classificador getInstancia() {
        
        if(instancia == null){
            instancia = new Classificador();
        }
                    
        return instancia;
    }
    
  

    /**
     * Classifica uma lista do tipo Ordenavel 
     * utilizando o método de HeapSort.
     *
     * @param ordenaveis lista a ser classificada.
     * @return ordenaveis lista classificada.
     */

    public List<T> classificar(List<T> ordenaveis) {
        buildMaxHeap(ordenaveis);
        int tamanhoLista = ordenaveis.size();

        for (int i = ordenaveis.size() - UM; i > ZERO; i--) {
            swap(ordenaveis, i, ZERO);
            maxHeapify(ordenaveis, ZERO, --tamanhoLista);
        }

        return ordenaveis;
    }

    /**
     * Ordena a lista verificando todos os nós, utilizando a estrutura de árvore binária..
     *
     * @param ordenaveis list to sort.
     */
    private void buildMaxHeap(List<T> ordenaveis) {
        for (int i = ordenaveis.size() / DOIS - UM; i >= ZERO; i--) {
            maxHeapify(ordenaveis, i, ordenaveis.size());
        }
    }

    /**
     * Utiliza árvore binária para classificar a lista de elementos,
     * Nesta estrutura, a raiz precisa ser maior ou igual aos nós filhos.
     * A comparacao é feita através do método Ordenavel.parametroDeOrdenacao().doubleValue
     * Quando os nós filhos são maiores que a sua raiz, as posicoes dos itens são trocadas.
     * repetindo o processo até a lista estar devidamente ordenada.
     *
     * @param ordenaveis lista a ser classificada.
     * @param posicao         A atual posicao do item na lista.
     * @param tamanhoLista             o tamanho da lista.
     */
    private void maxHeapify(List<T> ordenaveis, int posicao, int tamanhoLista) {
        int maxi;
        // filhos
        int esquerda = DOIS * posicao;
        int direita = DOIS * posicao + UM;
        if ((esquerda < tamanhoLista) && (ordenaveis.get(esquerda).parametroDeOrdenacao().doubleValue() >ordenaveis.get(posicao).parametroDeOrdenacao().doubleValue())) {
            maxi = esquerda;
        } else {
            maxi = posicao;
        }
        if (direita < tamanhoLista && ordenaveis.get(direita).parametroDeOrdenacao().doubleValue() > ordenaveis.get(maxi).parametroDeOrdenacao().doubleValue()) {
            maxi = direita;
        }
        if (maxi != posicao) {
            swap(ordenaveis, posicao, maxi);
            maxHeapify(ordenaveis, maxi, tamanhoLista);
        }
    }

    /**
     * Troca as posicoes dos itens na lista.
     *
     * @param ordenaveis lista que esta sendo ordenada.
     * @param indiceAtual      posicao atual do item.
     * @param indiceAposTroca    nova posicao do item.
     */
    private void swap(List<T> ordenaveis, int indiceAtual, int indiceAposTroca) {
        T aux = ordenaveis.get(indiceAtual);
        ordenaveis.set(indiceAtual, ordenaveis.get(indiceAposTroca));
        ordenaveis.set(indiceAposTroca, aux);
    }
    
}
