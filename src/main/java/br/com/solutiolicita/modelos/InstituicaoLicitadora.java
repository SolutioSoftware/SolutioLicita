package br.com.solutiolicita.modelos;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_instituicao_licitadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InstituicaoLicitadora.findAll", query = "SELECT i FROM InstituicaoLicitadora i"),
    @NamedQuery(name = "InstituicaoLicitadora.findById", query = "SELECT i FROM InstituicaoLicitadora i WHERE i.id = :id"),
    @NamedQuery(name = "InstituicaoLicitadora.findByIdPessoaJuridica", query = "SELECT i FROM InstituicaoLicitadora i WHERE i.pessoaJuridica = :idPessoaJuridica")})
public class InstituicaoLicitadora implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JoinColumn(name = "id_pessoa_juridica", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private PessoaJuridica pessoaJuridica;
    
    @OneToMany(mappedBy = "instituicaoLicitadora")
    private transient Set<Pregao> pregoes;

    public InstituicaoLicitadora() {
        pessoaJuridica = new PessoaJuridica();
    }

    public InstituicaoLicitadora(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }
    
    public Set<Pregao> getPregoes() {
        return pregoes;
    }

    public void setPregoes(Set<Pregao> pregoes) {
        this.pregoes = pregoes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof InstituicaoLicitadora)) {
            return false;
        }
        InstituicaoLicitadora other = (InstituicaoLicitadora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.InstituicaoLicitadora[ instituicaoLicitadoraPK=" + id + " ]";
    }

    
}
