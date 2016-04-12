package br.com.solutiolicita.modelos;

/**
 *
 * @author Matheus Oliveira
 */
public enum ENUMStatusPregao {
    
    ABERTO("Aberto"),
    CANCELADO("Cancelado"),
    ANULADO("Anulado"),
    DESERTO("Deserto"), 
    FRACASSADO("Fracassado"), 
    CONCLUIDO_SEM_RECURSO("Concluído sem Recurso"),
    CONCLUIDO_COM_RECURSO("Concluído com Recurso");
    
    private final String statusPregao;

    private ENUMStatusPregao(String statusPregao) {
        this.statusPregao = statusPregao;
    }
    
    public String getStatusPregao(){
        return this.statusPregao;
    }
}
