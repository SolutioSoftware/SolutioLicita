package br.com.solutiolicita.modelos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_lance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lance.findAll", query = "SELECT l FROM Lance l"),
    @NamedQuery(name = "Lance.findById", query = "SELECT l FROM Lance l WHERE l.id = :id"),
    @NamedQuery(name = "Lance.findByHorarioLance", query = "SELECT l FROM Lance l WHERE l.horarioLance = :horarioLance"),
    @NamedQuery(name = "Lance.findByValor", query = "SELECT l FROM Lance l WHERE l.valor = :valor")})
public class Lance implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_lance")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioLance;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigInteger valor;
    
    @PrimaryKeyJoinColumn(name = "id_licitante", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EmpresaLicitante idLicitante;
    
    @JoinColumn(name = "id_item_pregao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemPregao idItemPregao;
    
    @JoinColumn(name = "id_sessao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sessao idSessao;

    public Lance() {
    }

    public Lance(Long id) {
        this.id = id;
    }

    public Lance(Long id, Date horarioLance, BigInteger valor) {
        this.id = id;
        this.horarioLance = horarioLance;
        this.valor = valor;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHorarioLance() {
        return horarioLance;
    }

    public void setHorarioLance(Date horarioLance) {
        this.horarioLance = horarioLance;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
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
        if (!(object instanceof Lance)) {
            return false;
        }
        Lance other = (Lance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.Lance[ id=" + id + " ]";
    }
    
}
