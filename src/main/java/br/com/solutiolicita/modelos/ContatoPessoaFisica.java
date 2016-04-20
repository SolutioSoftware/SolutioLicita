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
@Embeddable
@Table(name = "tbl_contato_pessoa_fisica")
public class ContatoPessoaFisica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 9, max = 13)
    @Column(name = "telefone")
    private String telefone;

    @Size(max = 80)
    @Column(name = "email")
    private String email;

    @Size(min = 9, max = 13)
    @Column(name = "celular")
    private String celular;

    private PessoaFisica idPessoaFisica;

    public ContatoPessoaFisica() {
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public PessoaFisica getIdPessoaFisica() {
        return idPessoaFisica;
    }

    public void setIdPessoaFisica(PessoaFisica idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
    }

}
