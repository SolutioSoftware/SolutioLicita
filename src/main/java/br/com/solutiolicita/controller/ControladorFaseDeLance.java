package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.IteradorRodada;
import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.excecoes.ExcecoesLicita;
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
    private IteradorRodada iteradorRodada;
    private List<ItemPregao> itens;
    private List<Proposta> propostas;
    private List<Lance> lances;
    private ItemPregao itemPregao;
    private int contItemCorrente;
    private int indiceItemCorrente;
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
        iteradorRodada = new IteradorRodada();
        itens = new ArrayList();
        propostas = new ArrayList();
        lances = new ArrayList();
        contItemCorrente = 1;
        indiceItemCorrente = 0;
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
            licitanteRodada = iteradorRodada.proximo(propostas);
        } else {
            JsfUtil.addErrorMessage("O Lance tem que ser inferior aos antigos!");
        }

    }

    public void declinarProposta() {
        try {
            iteradorRodada.remocao(propostas);
            licitanteRodada = iteradorRodada.proximo(propostas);
        } catch (ExcecoesLicita e) {
            Logger.getGlobal().log(Level.WARNING, e.getMessage());
            JsfUtil.addErrorMessage(e.getMessage());
        }
    }

    public String encerrarItem() {
        indiceItemCorrente++;
        contItemCorrente++;
        iteradorRodada.reiniciarIterador();
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
                criarPrimeiroLance();
                
            }
        }
        if (propostas.isEmpty()) {
            propostas = new ArrayList();
        }
    }
    
    public void criarPrimeiroLance(){
        Lance primeiroLance = new Lance();
        primeiroLance.setValor(melhorProposta.getValorUnitario());
        primeiroLance.setIdItemPregao(melhorProposta.getIdItemPregao());
        primeiroLance.setIdLicitante(melhorProposta.getIdLicitante());
        primeiroLance.setIdSessao(sessao);
        ultimoLance = primeiroLance;
        servicoSessao.salvarLance(primeiroLance);
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
