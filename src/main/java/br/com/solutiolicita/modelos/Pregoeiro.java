package br.com.solutiolicita.modelos;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_pregoeiro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregoeiro.findAll", query = "SELECT p FROM Pregoeiro p"),
    @NamedQuery(name = "Pregoeiro.findById", query = "SELECT p FROM Pregoeiro p WHERE p.id = :id"),
    @NamedQuery(name = "Pregoeiro.findByIdPessoaFisica", query = "SELECT p FROM Pregoeiro p WHERE p.pessoaFisica = :idPessoaFisica")})
public class Pregoeiro implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregoeiro")
    private transient Set<Sessao> sessaoSet;
    
    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "idPregoeiro")
    private Login login;
    
    @JoinColumn(name = "id_pessoa_fisica", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private PessoaFisica pessoaFisica;

    public Pregoeiro() {
        pessoaFisica = new PessoaFisica();
        login = new Login();
    }

    public Pregoeiro(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public Set<Sessao> getSessaoSet() {
        return sessaoSet;
    }

    public void setSessaoSet(Set<Sessao> sessaoSet) {
        this.sessaoSet = sessaoSet;
    }

    @XmlTransient
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pregoeiro)) {
            return false;
        }
        Pregoeiro other = (Pregoeiro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pregoeiro{" + "id=" + id + ", sessaoSet=" + sessaoSet + ", login=" + login + '}';
    }

   

    
}
