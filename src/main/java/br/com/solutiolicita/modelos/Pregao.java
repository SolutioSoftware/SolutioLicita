package br.com.solutiolicita.modelos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_pregao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregao.findAll", query = "SELECT p FROM Pregao p"),
    @NamedQuery(name = "Pregao.findById", query = "SELECT p FROM Pregao p WHERE p.id = :id"),
    @NamedQuery(name = "Pregao.findByNumeroProcesso", query = "SELECT p FROM Pregao p WHERE p.numeroProcesso = :numeroProcesso"),
    @NamedQuery(name = "Pregao.findByNumeroPregao", query = "SELECT p FROM Pregao p WHERE p.numeroPregao = :numeroPregao"),
    @NamedQuery(name = "Pregao.findByDescricao", query = "SELECT p FROM Pregao p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Pregao.findByStatusPregao", query = "SELECT p FROM Pregao p WHERE p.statusPregao = :statusPregao"),
    @NamedQuery(name = "Pregao.findBySincronizado", query = "SELECT p FROM Pregao p WHERE p.sincronizado = :sincronizado")})
public class Pregao implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_processo", unique = true)
    private String numeroProcesso;
    
    @Size(max = 20)
    @Column(name = "numero_pregao", unique = true)
    private String numeroPregao;
    
    @Size(max = 255)
    @Column(name = "descricao")
    private String descricao;
    
    @Size(max = 10)
    @Column(name = "status_pregao")
    private String statusPregao;
    
    @Column(name = "sincronizado")
    private Boolean sincronizado;
    
    @JoinTable(name = "tbl_historico_status_pregao", joinColumns = {
        @JoinColumn(name = "id_pregao", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_status", referencedColumnName = "id")})
    @ManyToMany
    private transient Set<StatusPregao> statusPregaoSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregao", orphanRemoval = true)
    private Set<ItemPregao> itensPregoes;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregao")
    private transient Set<Sessao> sessaoSet;
    
    @ManyToOne
    @JoinColumn(name = "instituicaoLicitadora", referencedColumnName = "id")
    private InstituicaoLicitadora instituicaoLicitadora;

    public Pregao() {
        this.itensPregoes = new HashSet<>();
    }

    public Pregao(Long id) {
        this.id = id;
    }

    public Pregao(Long id, String numeroProcesso) {
        this.id = id;
        this.numeroProcesso = numeroProcesso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getNumeroPregao() {
        return numeroPregao;
    }

    public void setNumeroPregao(String numeroPregao) {
        this.numeroPregao = numeroPregao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatusPregao() {
        return statusPregao;
    }

    public void setStatusPregao(String statusPregao) {
        this.statusPregao = statusPregao;
    }

    public Boolean getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Boolean sincronizado) {
        this.sincronizado = sincronizado;
    }

    @XmlTransient
    public Set<StatusPregao> getStatusPregaoSet() {
        return statusPregaoSet;
    }

    public void setStatusPregaoSet(Set<StatusPregao> statusPregaoSet) {
        this.statusPregaoSet = statusPregaoSet;
    }

    public Set<ItemPregao> getItensPregoes() {
        return itensPregoes;
    }

    public void setItensPregoes(Set<ItemPregao> itensPregoes) {
        this.itensPregoes = itensPregoes;
    }

    
    @XmlTransient
    public Set<Sessao> getSessaoSet() {
        return sessaoSet;
    }

    public void setSessaoSet(Set<Sessao> sessaoSet) {
        this.sessaoSet = sessaoSet;
    }
    
    public InstituicaoLicitadora getInstituicaoLicitadora() {
        return instituicaoLicitadora;
    }

    public void setInstituicaoLicitadora(InstituicaoLicitadora instituicaoLicitadora) {
        this.instituicaoLicitadora = instituicaoLicitadora;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pregao)) {
            return false;
        }
        Pregao other = (Pregao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.Pregao[ id=" + id + " ]";
    }
    
}
