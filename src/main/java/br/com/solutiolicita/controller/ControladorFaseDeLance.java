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
import br.com.solutiolicita.servicos.ServicoItemIF;
import br.com.solutiolicita.servicos.ServicoSessaoIF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
    private List<Lance> lances;
    private ItemPregao itemPregao;
    private int contItemCorrente;
    private int indiceItemCorrente;
    private final int IND_MELHOR_PROPOSTA = 0;
    private final int IND_PIOR_PROPOSTA = 0;
    private final int QUANT_MIN_LANCES = 1;
    private final int QUANT_MIN_PROPOSTAS = 1;

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
        itemPregao.setStatusItem(ENUMStatusItemPregao.COM_VENCEDOR);
        servicoSessao.atualizarStatusItemPregao(itemPregao);
        if (proximoItem()) {
            lances = new ArrayList();
            carregarFaseDeLance();
            return null;
        }
        //Retornar para a página de resultados.
        return "/restrito/sessao/sessao.xhtml";
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
                    naoTemLance = proximoItem();
                }
            }
            return null;
        } else {
            JsfUtil.addErrorMessage("Este Pregão não possui nenhum item!");
            return "/restrito/sessao/sessao.xhtml";
        }
    }

//                    List<Lance> lancesExis = servicoSessao.buscarLances(itemPregao);
//                    if (propostas.size() > QUANT_MIN_PROPOSTAS) {
//                        propostas = servicoSessao.classificarPropostas(propostas);
//                        melhorProposta = propostas.get(IND_MELHOR_PROPOSTA);
//                        licitanteRodada = melhorProposta.getIdLicitante();
//                        if (lancesExis.isEmpty()) {
//                            criarPrimeiroLance();
//                            naoTemLance = false;
//                        } else if (lancesExis.size() > QUANT_MIN_LANCES) {
//                            naoTemLance = proximoItem();
//                        } else {
//                            ultimoLance = lancesExis.get(IND_MELHOR_PROPOSTA);
//                            naoTemLance = false;
//                        }
//                    } else if (lancesExis.size() <= QUANT_MIN_LANCES) {
//                        naoTemLance = false;
//                    } else {
//                        naoTemLance = proximoItem();
//                    }
//                }
//                if (!(indiceItemCorrente < itens.size())) {
//                    JsfUtil.addSuccessMessage("Não possui mais itens a serem leiloados!");
//                    return "/restrito/sessao/sessao.xhtml";
//                }
//            }
    public void criarPrimeiroLance() {
        lances = servicoSessao.buscarLances(itemPregao);
        if (lances.isEmpty()) {
            Lance primeiroLance = new Lance();
            primeiroLance.setValor(melhorProposta.getValorUnitario());
            primeiroLance.setIdItemPregao(melhorProposta.getIdItemPregao());
            primeiroLance.setIdLicitante(melhorProposta.getIdLicitante());
            primeiroLance.setIdSessao(sessao);
            ultimoLance = primeiroLance;
            servicoSessao.salvarLance(primeiroLance);
        }else{
            ultimoLance = lances.get(lances.size()-1);
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

    private void limparDados() {
        iteradorRodada = new IteradorRodada();
        itens = new ArrayList();
        propostas = new ArrayList();
        contItemCorrente = 1;
        indiceItemCorrente = 0;
    }

}
