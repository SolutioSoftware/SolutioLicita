package br.com.solutiolicita.modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_contato_pessoa_fisica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContatoPessoaFisica.findAll", query = "SELECT c FROM ContatoPessoaFisica c"),
    @NamedQuery(name = "ContatoPessoaFisica.findById", query = "SELECT c FROM ContatoPessoaFisica c WHERE c.id = :id")})
public class ContatoPessoaFisica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Size(min = 9, max = 13)
    @Column(name = "telefone")
    private String telefone;

    @Size(max = 80)
    @Column(name = "email")
    private String email;

    @Size(min = 9, max = 13)
    @Column(name = "celular")
    private String celular;

    @JoinColumn(name = "id_pessoa_fisica", referencedColumnName = "id")
    @OneToOne(optional = false)
    private PessoaFisica idPessoaFisica;

    public ContatoPessoaFisica() {
    }

    public ContatoPessoaFisica(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContatoPessoaFisica)) {
            return false;
        }
        ContatoPessoaFisica other = (ContatoPessoaFisica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.ContatoPessoaFisica[ id=" + id + " ]";
    }

}
