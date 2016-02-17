package br.com.solutiolicita.modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_pessoa_fisica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PessoaFisica.findAll", query = "SELECT p FROM PessoaFisica p"),
    @NamedQuery(name = "PessoaFisica.findById", query = "SELECT p FROM PessoaFisica p WHERE p.id = :id"),
    @NamedQuery(name = "PessoaFisica.findByNome", query = "SELECT p FROM PessoaFisica p WHERE p.nome = :nome"),
    @NamedQuery(name = "PessoaFisica.findByCpf", query = "SELECT p FROM PessoaFisica p WHERE p.cpf = :cpf"),
    @NamedQuery(name = "PessoaFisica.findByRg", query = "SELECT p FROM PessoaFisica p WHERE p.rg = :rg")})
public class PessoaFisica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Size(max = 80)
    @Column(name = "nome")
    private String nome;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "cpf", unique = true)
    private String cpf;

    @Size(max = 30)
    @Column(name = "rg")
    private String rg;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoaFisica")
    private MembroApoio membroApoio;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPessoaFisica")
    private ContatoPessoaFisica contatoPessoaFisica;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoaFisica")
    private RepresentanteLegal representanteLegal;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoaFisica")
    private Pregoeiro pregoeiro;

    @Embedded
    private Endereco endereco;

    public PessoaFisica() {
        endereco = new Endereco();
        contatoPessoaFisica = new ContatoPessoaFisica();
    }

    public PessoaFisica(Long id) {
        this.id = id;
    }

    public PessoaFisica(Long id, String cpf) {
        this.id = id;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public MembroApoio getMembroApoio() {
        return membroApoio;
    }

    public void setMembroApoio(MembroApoio membroApoio) {
        this.membroApoio = membroApoio;
    }

    public ContatoPessoaFisica getContatoPessoaFisica() {
        return contatoPessoaFisica;
    }

    public void setContatoPessoaFisica(ContatoPessoaFisica contatoPessoaFisica) {
        this.contatoPessoaFisica = contatoPessoaFisica;
    }

    public RepresentanteLegal getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(RepresentanteLegal representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public Pregoeiro getPregoeiro() {
        return pregoeiro;
    }

    public void setPregoeiro(Pregoeiro pregoeiro) {
        this.pregoeiro = pregoeiro;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PessoaFisica)) {
            return false;
        }
        PessoaFisica other = (PessoaFisica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.PessoaFisica[ id=" + id + " ]";
    }

}
