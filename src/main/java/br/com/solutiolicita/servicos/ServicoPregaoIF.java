package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Pregao;
import java.util.Set;

/**
 *
 * @author ricardocaldeira
 */
public interface ServicoPregaoIF extends ServicoIF<Pregao> {
    public Set<ItemPregao> buscarItensPregoes(Pregao pregao);
}
