package br.com.solutiolicita.modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * TODO. refatorar entidade para resolver o problema do relacionamento com
 * pregão. A entidade será dividida em duas: Item e ItemDePregao, onde Item terá
 * somente a característica dos itens e ItemDePregao o Id do item, o id do
 * pregao e o valor do item para o referido pregao.
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_item_pregao", uniqueConstraints = @UniqueConstraint(columnNames = {"id_pregao", "id_item"}))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemPregao.findAll", query = "SELECT i FROM ItemPregao i"),
    @NamedQuery(name = "ItemPregao.findById", query = "SELECT i FROM ItemPregao i WHERE i.id = :id"),
    @NamedQuery(name = "ItemPregao.findByQuantidade", query = "SELECT i FROM ItemPregao i WHERE i.quantidade = :quantidade"),
    @NamedQuery(name = "ItemPregao.findByValorReferencia", query = "SELECT i FROM ItemPregao i WHERE i.valorReferencia = :valorReferencia"),
    @NamedQuery(name = "ItemPregao.findByStatusItem", query = "SELECT i FROM ItemPregao i WHERE i.statusItem = :statusItem"),
    @NamedQuery(name = "ItemPregao.findByPregao", query = "SELECT i FROM ItemPregao i WHERE i.pregao = :idPregao")})
public class ItemPregao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @ManyToOne (optional = false)
    @JoinColumn(name = "id_pregao", referencedColumnName = "id")
    private Pregao pregao;

    @ManyToOne (optional = false)
    @JoinColumn(name = "id_item", referencedColumnName = "id")
    private Item item;

    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private int quantidade;

    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_referencia")
    private BigDecimal valorReferencia;

    @Size(max = 15)
    @Column(name = "status_item")
    private String statusItem;

    @JoinTable(name = "tbl_historico_status_item_pregao", joinColumns = {
        @JoinColumn(name = "id_item_pregao", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_status", referencedColumnName = "id")})
    @ManyToMany
    private transient Set<StatusItemPregao> statusItemPregaoSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idItemPregao")
    private transient Set<Lance> lanceSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idItemPregao")
    private transient Set<Proposta> propostaSet;

    public ItemPregao() {
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorReferencia() {
        return valorReferencia;
    }

    public void setValorReferencia(BigDecimal valorReferencia) {
        this.valorReferencia = valorReferencia;
    }

    public String getStatusItem() {
        return statusItem;
    }

    public void setStatusItem(String statusItem) {
        this.statusItem = statusItem;
    }

    @XmlTransient
    public Set<StatusItemPregao> getStatusItemPregaoSet() {
        return statusItemPregaoSet;
    }

    public void setStatusItemPregaoSet(Set<StatusItemPregao> statusItemPregaoSet) {
        this.statusItemPregaoSet = statusItemPregaoSet;
    }

    @XmlTransient
    public Set<Lance> getLanceSet() {
        return lanceSet;
    }

    public void setLanceSet(Set<Lance> lanceSet) {
        this.lanceSet = lanceSet;
    }

    @XmlTransient
    public Set<Proposta> getPropostaSet() {
        return propostaSet;
    }

    public void setPropostaSet(Set<Proposta> propostaSet) {
        this.propostaSet = propostaSet;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.pregao);
        hash = 97 * hash + Objects.hashCode(this.item);
        hash = 97 * hash + this.quantidade;
        hash = 97 * hash + Objects.hashCode(this.valorReferencia);
        hash = 97 * hash + Objects.hashCode(this.statusItem);
        hash = 97 * hash + Objects.hashCode(this.statusItemPregaoSet);
        hash = 97 * hash + Objects.hashCode(this.lanceSet);
        hash = 97 * hash + Objects.hashCode(this.propostaSet);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemPregao other = (ItemPregao) obj;
        if (!Objects.equals(this.pregao, other.pregao)) {
            return false;
        }
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (!Objects.equals(this.valorReferencia, other.valorReferencia)) {
            return false;
        }
        if (!Objects.equals(this.statusItem, other.statusItem)) {
            return false;
        }
        if (!Objects.equals(this.statusItemPregaoSet, other.statusItemPregaoSet)) {
            return false;
        }
        if (!Objects.equals(this.lanceSet, other.lanceSet)) {
            return false;
        }
        if (!Objects.equals(this.propostaSet, other.propostaSet)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pregao getPregao() {
        return pregao;
    }

    public void setPregao(Pregao pregao) {
        this.pregao = pregao;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ItemPregao{" + "id=" + id + ", pregao=" + pregao + ", item=" + item + ", quantidade=" + quantidade + ", valorReferencia=" + valorReferencia + ", statusItem=" + statusItem + ", statusItemPregaoSet=" + statusItemPregaoSet + ", lanceSet=" + lanceSet + ", propostaSet=" + propostaSet + '}';
    }

}
