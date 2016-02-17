package br.com.solutiolicita.modelos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author WitaloCarlos
 */
@Table(name = "tbl_endereco")
@Embeddable
public class Endereco implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Size(max = 60)
    @Column(name = "logradouro")
    private String logradouro;
    
    @Size(max = 60)
    @Column(name = "cidade")
    private String cidade;
    
    @Size(max = 2)
    @Column(name = "estado")
    private String estado;
    
    @Size(max = 8)
    @Column(name = "cep")
    private String cep;
    
    @Column(name = "numero")
    private Integer numero;
    
    @Size(max = 50)
    @Column(name = "complemento")
    private String complemento;
    
    
    public Endereco() {
    }


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
}
