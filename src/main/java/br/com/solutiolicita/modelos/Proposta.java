package br.com.solutiolicita.modelos;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_proposta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proposta.findAll", query = "SELECT p FROM Proposta p"),
    @NamedQuery(name = "Proposta.findById", query = "SELECT p FROM Proposta p WHERE p.id = :id"),
    @NamedQuery(name = "Proposta.findBySessao", query = "SELECT p FROM Proposta p WHERE P.idSessao = :idSessao"),
    @NamedQuery(name = "Proposta.findBySessaoWithLicitante", query = "SELECT p FROM Proposta p WHERE p.idLicitante = :idLicitante AND p.idSessao = :idSessao" ),
    @NamedQuery(name = "Proposta.findByValorUnitario", query = "SELECT p FROM Proposta p WHERE p.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "Proposta.findByClassificada", query = "SELECT p FROM Proposta p WHERE p.classificada = :classificada")})
public class Proposta implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;
   
    @Column(name = "classificada")
    private Boolean classificada;
    
    @JoinColumn(name = "id_licitante", referencedColumnName = "id")
    @ManyToOne
    private EmpresaLicitante idLicitante;
    
    @JoinColumn(name = "id_item_pregao", referencedColumnName = "id")
    @ManyToOne
    private ItemPregao idItemPregao;
    
    @JoinColumn(name = "id_sessao", referencedColumnName = "id")
    @ManyToOne
    private Sessao idSessao;

    public Proposta() {
    }

    public Proposta(Long id) {
        this.id = id;
    }

    public Proposta(Long id, BigDecimal valorUnitario) {
        this.id = id;
        this.valorUnitario = valorUnitario;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Boolean getClassificada() {
        return classificada;
    }

    public void setClassificada(Boolean classificada) {
        this.classificada = classificada;
    }

    public EmpresaLicitante getIdLicitante() {
        return idLicitante;
    }

    public void setIdLicitante(EmpresaLicitante idLicitante) {
        this.idLicitante = idLicitante;
    }

    public ItemPregao getIdItemPregao() {
        return idItemPregao;
    }

    public void setIdItemPregao(ItemPregao idItemPregao) {
        this.idItemPregao = idItemPregao;
    }

    public Sessao getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Sessao idSessao) {
        this.idSessao = idSessao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Proposta)) {
            return false;
        }
        Proposta other = (Proposta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.Proposta[ id=" + id + " ]";
    }
    
}
