package br.com.solutiolicita.modelos;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_membro_apoio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MembroApoio.findAll", query = "SELECT m FROM MembroApoio m"),
    @NamedQuery(name = "MembroApoio.findById", query = "SELECT m FROM MembroApoio m WHERE m.id = :id"),
    @NamedQuery(name = "MembroApoio.findByIdPessoaFisica", query = "SELECT m FROM MembroApoio m WHERE m.pessoaFisica = :idPessoaFisica"),
    @NamedQuery(name = "MembroApoio.findByFuncao", query = "SELECT m FROM MembroApoio m WHERE m.funcao = :funcao")})
public class MembroApoio implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(max = 25)
    @Column(name = "funcao")
    private String funcao;
    
    @JoinColumn(name = "id_pessoa_fisica", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private PessoaFisica pessoaFisica;

    public MembroApoio() {
        pessoaFisica = new PessoaFisica();
    }

    public MembroApoio(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
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
        if (!(object instanceof MembroApoio)) {
            return false;
        }
        MembroApoio other = (MembroApoio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.MembroApoio[ membroApoioPK=" + id + " ]";
    }

   
}
