package br.com.solutiolicita.modelos;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "tbl_empresa_licitante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpresaLicitante.findAll", query = "SELECT e FROM EmpresaLicitante e"),
    @NamedQuery(name = "EmpresaLicitante.findById", query = "SELECT e FROM EmpresaLicitante e WHERE e.id = :id"),
    @NamedQuery(name = "EmpresaLicitante.findByIdPessoaJuridica", query = "SELECT e FROM EmpresaLicitante e WHERE e.pessoaJuridica = :idPessoaJuridica"),
    @NamedQuery(name = "EmpresaLicitante.findByInscricaoEstadual", query = "SELECT e FROM EmpresaLicitante e WHERE e.inscricaoEstadual = :inscricaoEstadual"),
    @NamedQuery(name = "EmpresaLicitante.findByTipoEmpresa", query = "SELECT e FROM EmpresaLicitante e WHERE e.tipoEmpresa = :tipoEmpresa"),
    @NamedQuery(name = "EmpresaLicitante.findByComplemento", query = "SELECT e FROM EmpresaLicitante e WHERE e.complemento = :complemento")})
public class EmpresaLicitante implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(max = 25)
    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tipo_empresa")
    private String tipoEmpresa;
    
    @Size(max = 150)
    @Column(name = "complemento")
    private String complemento;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLicitante")
    private transient Set<Lance> lanceSet;
    
    @JoinColumn(name = "id_pessoa_juridica", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private PessoaJuridica pessoaJuridica;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLicitante")
    private transient Set<Proposta> propostaSet;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_licitante", referencedColumnName = "id")
    private ContaBancaria contaBancaria;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "empresaLicitante")
    private RepresentanteLegal representanteLegal;
    
    @ManyToMany(mappedBy = "empresasLicitantes")
    private Set<Pregao> pregoes;

    public EmpresaLicitante() {
        pessoaJuridica = new PessoaJuridica();
        representanteLegal = new RepresentanteLegal();
        contaBancaria = new ContaBancaria();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @XmlTransient
    public Set<Lance> getLanceSet() {
        return lanceSet;
    }

    public void setLanceSet(Set<Lance> lanceSet) {
        this.lanceSet = lanceSet;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    @XmlTransient
    public Set<Proposta> getPropostaSet() {
        return propostaSet;
    }

    public void setPropostaSet(Set<Proposta> propostaSet) {
        this.propostaSet = propostaSet;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public RepresentanteLegal getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(RepresentanteLegal representanteLegal) {
        this.representanteLegal = representanteLegal;
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
        if (!(object instanceof EmpresaLicitante)) {
            return false;
        }
        EmpresaLicitante other = (EmpresaLicitante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EmpresaLicitante{" + "id=" + id + ", inscricaoEstadual=" + inscricaoEstadual + ", tipoEmpresa=" + tipoEmpresa + ", complemento=" + complemento + ", lanceSet=" + lanceSet + ", pessoaJuridica=" + pessoaJuridica + ", propostaSet=" + propostaSet + ", contaBancaria=" + contaBancaria + ", representanteLegal=" + representanteLegal + '}';
    }

 
    
    
}
