package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Lance;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.modelos.Proposta;
import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.servicos.ServicoSessaoIF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ricardocaldeira
 */
@Named
@ViewScoped
public class ControladorFaseDeLance implements Serializable {

    private Sessao sessao;
    private Lance lance;
    private Lance ultimoLance;
    private Proposta melhorProposta;
    private EmpresaLicitante licitanteRodada;
    private List<ItemPregao> itens;
    private List<Proposta> propostas;
    private List<Lance> lances;
    private ItemPregao itemPregao;
    private int contItemCorrente;
    private int indiceItemCorrente;
    private int indiceDoOfertante;
    private final int LIMITE_PROPOSTAS = 1;
    private final int IND_MELHOR_PROPOSTA = 0;

    @Inject
    private ServicoSessaoIF servicoSessao;

    public ControladorFaseDeLance() {
    }

    @PostConstruct
    public void iniciar() {
        sessao = new Sessao();
        lance = new Lance();
        ultimoLance = new Lance();
        itens = new ArrayList();
        propostas = new ArrayList();
        lances = new ArrayList();
        contItemCorrente = 1;
        indiceItemCorrente = 0;
        indiceDoOfertante = 0;
    }

    public void darLance() {
        if (servicoSessao.validarLance(lance, ultimoLance, melhorProposta)) {
            lance.setIdItemPregao(itemPregao);
            lance.setIdLicitante(getOfertante());
            lance.setIdSessao(sessao);
            servicoSessao.salvarLance(lance);
            lances.add(lance);
            ultimoLance = lance;
            lance = new Lance();
            passarVez();
        }else{
            JsfUtil.addErrorMessage("O Lance tem que ser inferior aos antigos!");
        }

    }

    private void passarVez() {
        indiceDoOfertante++;
        if (indiceDoOfertante >= propostas.size()) {
            indiceDoOfertante = 0;
            licitanteRodada = propostas.get(indiceDoOfertante).getIdLicitante();
        } else {
            licitanteRodada = propostas.get(indiceDoOfertante).getIdLicitante();
        }

    }

    public void declinarProposta() {
        if (propostas.size() >= LIMITE_PROPOSTAS) {
            propostas.remove(indiceDoOfertante);
        } else {
            JsfUtil.addErrorMessage("Não há mais Licitantes!");
        }
    }

    public String encerrarItem() {
        indiceItemCorrente++;
        contItemCorrente++;
        indiceDoOfertante = 0;
        if (indiceItemCorrente >= itens.size()) {
            return "/restrito/sessao/sessao.xhtml";
        }
        ultimoLance = new Lance();
        carregarFaseDeLance();
        lances = new ArrayList();
        return null;
    }

    private void buscarItensPregao() {
        if (itens.isEmpty()) {
            itens = this.servicoSessao.buscarItensPregao(sessao.getIdPregao());
            if (itens.isEmpty()) {
                itens = new ArrayList();
            }
        }
    }

    public void carregarFaseDeLance() {
        buscarItensPregao();
        if (!itens.isEmpty()) {
            Logger.getGlobal().log(Level.INFO, "indice do item: {0}", indiceItemCorrente);
            itemPregao = itens.get(indiceItemCorrente);
            propostas = servicoSessao.buscarPropostas(itemPregao);
            if (!propostas.isEmpty()) {
                propostas = servicoSessao.classificarPropostas(propostas);
                melhorProposta = propostas.get(IND_MELHOR_PROPOSTA);
                licitanteRodada = propostas.get(IND_MELHOR_PROPOSTA).getIdLicitante();
            }
        }
        if (propostas.isEmpty()) {
            propostas = new ArrayList();
        }
    }

    public Pregao getPregao() {
        return sessao.getIdPregao();
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public ItemPregao getItemPregao() {
        carregarFaseDeLance();
        return itemPregao;
    }

    public void setItemPregao(ItemPregao itemPregao) {
        this.itemPregao = itemPregao;
    }

    public int getIndiceDoItem() {
        return contItemCorrente;
    }

    public Proposta getMelhorProposta() {
        return melhorProposta;
    }

    public EmpresaLicitante getOfertante() {
        return licitanteRodada;
    }

    public Lance getLance() {
        return lance;
    }

    public void setLance(Lance lance) {
        this.lance = lance;
    }

    public Lance getUltimoLance() {
        return ultimoLance;
    }

    public void setUltimoLance(Lance ultimoLance) {
        this.ultimoLance = ultimoLance;
    }

    public List<Lance> getLances() {
        return lances;
    }

    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }

}
