package br.com.solutiolicita.modelos;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_status_sessao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusSessao.findAll", query = "SELECT s FROM StatusSessao s"),
    @NamedQuery(name = "StatusSessao.findById", query = "SELECT s FROM StatusSessao s WHERE s.id = :id"),
    @NamedQuery(name = "StatusSessao.findByValor", query = "SELECT s FROM StatusSessao s WHERE s.valor = :valor")})
public class StatusSessao implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 20)
    @Column(name = "valor")
    private String valor;
    @ManyToMany(mappedBy = "statusSessaoSet")
    private transient Set<Sessao> sessaoSet;

    public StatusSessao() {
    }

    public StatusSessao(Long id) {
        this.id = id;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @XmlTransient
    public Set<Sessao> getSessaoSet() {
        return sessaoSet;
    }

    public void setSessaoSet(Set<Sessao> sessaoSet) {
        this.sessaoSet = sessaoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof StatusSessao)) {
            return false;
        }
        StatusSessao other = (StatusSessao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.StatusSessao[ id=" + id + " ]";
    }
    
}
