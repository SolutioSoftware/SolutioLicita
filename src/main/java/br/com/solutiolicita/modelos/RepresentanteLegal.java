package br.com.solutiolicita.modelos;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_representante_legal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepresentanteLegal.findAll", query = "SELECT r FROM RepresentanteLegal r"),
    @NamedQuery(name = "RepresentanteLegal.findById", query = "SELECT r FROM RepresentanteLegal r WHERE r.id = :id"),
    @NamedQuery(name = "RepresentanteLegal.findByIdPessoaFisica", query = "SELECT r FROM RepresentanteLegal r WHERE r.pessoaFisica = :idPessoaFisica"),
    @NamedQuery(name = "RepresentanteLegal.findByIdLicitante", query = "SELECT r FROM RepresentanteLegal r WHERE r.empresaLicitante = :idLicitante")})
public class RepresentanteLegal implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @JoinColumn(name = "id_licitante", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private EmpresaLicitante empresaLicitante;
    
    @JoinColumn(name = "id_pessoa_fisica", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private PessoaFisica pessoaFisica;

    public RepresentanteLegal() {
        pessoaFisica = new PessoaFisica();
    }

    public RepresentanteLegal(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmpresaLicitante getEmpresaLicitante() {
        return empresaLicitante;
    }

    public void setEmpresaLicitante(EmpresaLicitante empresaLicitante) {
        this.empresaLicitante = empresaLicitante;
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
        if (!(object instanceof RepresentanteLegal)) {
            return false;
        }
        RepresentanteLegal other = (RepresentanteLegal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.RepresentanteLegal[ representanteLegalPK=" + id + " ]";
    }

  
}
