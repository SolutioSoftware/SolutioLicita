package br.com.solutiolicita.modelos;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_sessao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sessao.findAll", query = "SELECT s FROM Sessao s"),
    @NamedQuery(name = "Sessao.findById", query = "SELECT s FROM Sessao s WHERE s.id = :id"),
    @NamedQuery(name = "Sessao.findByHorarioInicio", query = "SELECT s FROM Sessao s WHERE s.horarioInicio = :horarioInicio"),
    @NamedQuery(name = "Sessao.findByHorarioEncerramento", query = "SELECT s FROM Sessao s WHERE s.horarioEncerramento = :horarioEncerramento"),
    @NamedQuery(name = "Sessao.findByDataRealizacao", query = "SELECT s FROM Sessao s WHERE s.dataRealizacao = :dataRealizacao"),
    @NamedQuery(name = "Sessao.findByHorarioPrevisto", query = "SELECT s FROM Sessao s WHERE s.horarioPrevisto = :horarioPrevisto"),
    @NamedQuery(name = "Sessao.findByStatusSessao", query = "SELECT s FROM Sessao s WHERE s.statusSessao = :statusSessao")})
public class Sessao implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "horario_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioInicio;
    
    @Column(name = "horario_encerramento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioEncerramento;
    
    @Column(name = "data_realizacao")
    @Temporal(TemporalType.DATE)
    private Date dataRealizacao;
    
    @Column(name = "horario_previsto")
    @Temporal(TemporalType.TIME)
    private Date horarioPrevisto;
    
    @Size(max = 20)
    @Column(name = "status_sessao")
    private String statusSessao;
    
    @JoinTable(name = "tbl_historico_status_sessao", joinColumns = {
        @JoinColumn(name = "id_sessao", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_status", referencedColumnName = "id")})
    @ManyToMany
    private transient Set<StatusSessao> statusSessaoSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSessao")
    private transient Set<Lance> lanceSet;
    
    @JoinColumn(name = "id_pregao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pregao idPregao;
    
    @PrimaryKeyJoinColumn(name = "id_pregoeiro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pregoeiro idPregoeiro;
    
    @OneToMany(mappedBy = "idSessao")
    private transient Set<Proposta> propostaSet;

    public Sessao() {
    }

    public Sessao(Long id) {
        this.id = id;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioEncerramento() {
        return horarioEncerramento;
    }

    public void setHorarioEncerramento(Date horarioEncerramento) {
        this.horarioEncerramento = horarioEncerramento;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Date getHorarioPrevisto() {
        return horarioPrevisto;
    }

    public void setHorarioPrevisto(Date horarioPrevisto) {
        this.horarioPrevisto = horarioPrevisto;
    }

    public String getStatusSessao() {
        return statusSessao;
    }

    public void setStatusSessao(String statusSessao) {
        this.statusSessao = statusSessao;
    }

    @XmlTransient
    public Set<StatusSessao> getStatusSessaoSet() {
        return statusSessaoSet;
    }

    public void setStatusSessaoSet(Set<StatusSessao> statusSessaoSet) {
        this.statusSessaoSet = statusSessaoSet;
    }

    @XmlTransient
    public Set<Lance> getLanceSet() {
        return lanceSet;
    }

    public void setLanceSet(Set<Lance> lanceSet) {
        this.lanceSet = lanceSet;
    }

    public Pregao getIdPregao() {
        return idPregao;
    }

    public void setIdPregao(Pregao idPregao) {
        this.idPregao = idPregao;
    }

    public Pregoeiro getIdPregoeiro() {
        return idPregoeiro;
    }

    public void setIdPregoeiro(Pregoeiro idPregoeiro) {
        this.idPregoeiro = idPregoeiro;
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sessao)) {
            return false;
        }
        Sessao other = (Sessao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.Sessao[ id=" + id + " ]";
    }
    
}
