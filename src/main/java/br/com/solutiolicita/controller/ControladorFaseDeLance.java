package br.com.solutiolicita.controller;

import br.com.solutiolicita.controller.util.IteradorRodada;
import br.com.solutiolicita.controller.util.JsfUtil;
import br.com.solutiolicita.excecoes.ExcecoesLicita;
import br.com.solutiolicita.modelos.ENUMStatusItemPregao;
import br.com.solutiolicita.modelos.EmpresaLicitante;
import br.com.solutiolicita.modelos.ItemPregao;
import br.com.solutiolicita.modelos.Lance;
import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.modelos.Proposta;
import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.servicos.ServicoSessaoIF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ricardocaldeira
 */
@Named
@SessionScoped
public class ControladorFaseDeLance implements Serializable {

    private Sessao sessao;
    private Lance lance;
    private Lance ultimoLance;
    private Proposta melhorProposta;
    private EmpresaLicitante licitanteRodada;
    private IteradorRodada iteradorRodada;
    private List<ItemPregao> itens;
    private List<Proposta> propostas;
    private List<Lance> lancesVencedores;
    private List<Lance> lances;
    private ItemPregao itemPregao;
    private int contItemCorrente;
    private int indiceItemCorrente;
    private final int IND_MELHOR_PROPOSTA = 0;
    private final int IND_PIOR_PROPOSTA = 0;

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
        lancesVencedores = new ArrayList();
        contItemCorrente = 1;
        indiceItemCorrente = 0;
    }

    public void darLance() {
        if (servicoSessao.validarLance(lance, ultimoLance, melhorProposta)) {
            lance.setIdItemPregao(itemPregao);
            lance.setIdLicitante(getOfertante());
            lance.setIdSessao(sessao);
            lance.setHorarioLance(new Date());
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
        itemPregao.setStatusItem(ENUMStatusItemPregao.COM_VENCEDOR);
        servicoSessao.atualizarStatusItemPregao(itemPregao);
        if (proximoItem()) {
            lancesVencedores.add(lance);
            lances = new ArrayList();
            carregarFaseDeLance();
            return null;
        }
        //Retornar para a página de resultados.
        sessao.setStatusSessao("Encerrada");
        servicoSessao.atualizar(sessao);
        return "/restrito/pregao/pregaoResultado.xhtml";
    }

    public boolean proximoItem() {
        indiceItemCorrente++;
        contItemCorrente++;
        iteradorRodada.reiniciarIterador();
        return indiceItemCorrente < itens.size();
    }

    private void buscarItensPregao() {
        if (itens.isEmpty()) {
            itens = this.servicoSessao.buscarItensPregao(sessao.getIdPregao());
        }
    }

    public String iniciarPregao() {
        limparDados();
        buscarItensPregao();
        return carregarFaseDeLance();
    }

    public String carregarFaseDeLance() {
        if (!itens.isEmpty()) {
            Logger.getGlobal().log(Level.INFO, "indice do item: {0}", indiceItemCorrente);
            /*Loop para verificar qual itemPregao ainda não foi leiloado*/
            boolean naoTemLance = true;
            while (naoTemLance) {
                itemPregao = itens.get(indiceItemCorrente);
                if (itemPregao.getStatusItem() == null) {
                    propostas = servicoSessao.buscarPropostas(itemPregao);
                    if (propostas.size() <= 3) {
                        if (propostas.isEmpty()) {
                            itemPregao.setStatusItem(ENUMStatusItemPregao.DESERTO);
                            servicoSessao.atualizarStatusItemPregao(itemPregao);
                            naoTemLance = proximoItem();
                        } else {
                            propostas = servicoSessao.classificarPropostas(propostas);
                            melhorProposta = propostas.get(IND_MELHOR_PROPOSTA);
                            Collections.reverse(propostas);
                            licitanteRodada = propostas.get(IND_PIOR_PROPOSTA).getIdLicitante();
                            //ordernar do maior para o menor e manter os 3, e vai a fase de lances
                            criarPrimeiroLance();
                            naoTemLance = false;
                        }
                    } else {
                        propostas = servicoSessao.classificarPropostas(propostas);
                        melhorProposta = propostas.get(IND_MELHOR_PROPOSTA);
                        Collections.reverse(propostas);
                        licitanteRodada = propostas.get(IND_PIOR_PROPOSTA).getIdLicitante();
                        criarPrimeiroLance();
                        naoTemLance = false;
                        //Classificar as propostas, obedecendo o valor mínimo de empresas concorrentes(3)
                    }
                } else {
                    selecionarLanceVencedor();
                    naoTemLance = proximoItem();
                }
            }
            return null;
        } else {
            JsfUtil.addErrorMessage("Este Pregão não possui nenhum item!");
            return "/restrito/sessao/sessao.xhtml";
        }
    }

    public void criarPrimeiroLance() {
        lances = servicoSessao.buscarLances(itemPregao);
        if (lances.isEmpty()) {
            Lance primeiroLance = new Lance();
            primeiroLance.setValor(melhorProposta.getValorUnitario());
            primeiroLance.setIdItemPregao(melhorProposta.getIdItemPregao());
            primeiroLance.setIdLicitante(melhorProposta.getIdLicitante());
            primeiroLance.setIdSessao(sessao);
            primeiroLance.setHorarioLance(new Date());
            ultimoLance = primeiroLance;
            servicoSessao.salvarLance(primeiroLance);
        } else {
            ultimoLance = lances.get(lances.size() - 1);
        }
    }
    
    public String pausarSessao(){
        sessao.setStatusSessao("Pausada");
        servicoSessao.atualizar(sessao);
        return "/restrito/index.xhtml";
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

    public List<Lance> getLancesVencedores() {
        return lancesVencedores;
    }

    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }

    private void limparDados() {
        iteradorRodada = new IteradorRodada();
        itens = new ArrayList();
        propostas = new ArrayList();
        lancesVencedores = new ArrayList();
        contItemCorrente = 1;
        indiceItemCorrente = 0;
    }

    private void selecionarLanceVencedor() {
        List<Lance> lancesDoItem = servicoSessao.buscarLances(itemPregao);
        if (lancesDoItem.size() > 1) {
            lancesVencedores.add(lancesDoItem.get(lancesDoItem.size() - 1));
        }
    }

}
