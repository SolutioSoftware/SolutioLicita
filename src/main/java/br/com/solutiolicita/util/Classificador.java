package br.com.solutiolicita.util;

import java.util.Comparator;

/**
 * Implementação do algoritmo HeapSort para classificação de objetos do tipo
 * Ordenavel
 *
 * @author WitaloCarlos
 */
public class Classificador implements Comparator<Ordenavel> {

    private static Classificador instancia = null;

    @Override
    public int compare(Ordenavel o1, Ordenavel o2) {
        return (new Double(o1.parametroDeOrdenacao().doubleValue()).compareTo(o2.parametroDeOrdenacao().doubleValue()));
    }

    public static Classificador getInstancia() {
        if (instancia == null) {
            instancia = new Classificador();
        }
        return instancia;
    }
}
