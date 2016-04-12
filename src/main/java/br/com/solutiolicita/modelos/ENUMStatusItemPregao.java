package br.com.solutiolicita.modelos;

/**
 *
 * @author Matheus Oliveira
 */
public enum ENUMStatusItemPregao {
    
    SEM_PROPOSTA("Sem Proposta"),
    ADJUDICADO("Adjudicado"),
    DESERTO("Deserto"),
    FRACASSADO("Fracassado"),
    INABILITADO("Inabilitado"),
    REVOGADO("Revogado"),
    COM_VENCEDOR("Com Vencedor");
    
    private final String statusItemPregao;

    private ENUMStatusItemPregao(String statusItemPregao) {
        this.statusItemPregao = statusItemPregao;
    }
    
    public String getStatusItemPregao(){
        return this.statusItemPregao;
    }
}
