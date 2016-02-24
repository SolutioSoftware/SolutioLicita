package br.com.solutiolicita.servicos;

import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Pregao;
import java.util.List;

/**
 *
 * @author ricardocaldeira
 */
public interface ServicoPregaoIF extends ServicoIF<Pregao> {
    public List<ItemPregao> buscarItensPregoes(Pregao pregao);
}
