package br.com.solutiolicita.modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_contato_pessoa_juridica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContatoPessoaJuridica.findAll", query = "SELECT c FROM ContatoPessoaJuridica c"),
    @NamedQuery(name = "ContatoPessoaJuridica.findById", query = "SELECT c FROM ContatoPessoaJuridica c WHERE c.id = :id")})
public class ContatoPessoaJuridica implements Serializable {
    
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
    @Pattern(regexp = "^$|^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$",
            message = "E-mail incorreto")
    @Column(name = "email")
    private String email;
    
    @Size(min = 9, max = 13)
    @Column(name = "celular")
    private String celular;
    
    @JoinColumn(name = "id_pessoa_juridica", referencedColumnName = "id")
    @OneToOne(optional = false)
    private PessoaJuridica idPessoaJuridica;

    public ContatoPessoaJuridica() {
    }

    public ContatoPessoaJuridica(Long id) {
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

   
    
    public PessoaJuridica getIdPessoaJuridica() {
        return idPessoaJuridica;
    }

    public void setIdPessoaJuridica(PessoaJuridica idPessoaJuridica) {
        this.idPessoaJuridica = idPessoaJuridica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContatoPessoaJuridica)) {
            return false;
        }
        ContatoPessoaJuridica other = (ContatoPessoaJuridica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.ContatoPessoaJuridica[ id=" + id + " ]";
    }
    
}
