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
@Table(name = "tbl_pessoa_juridica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PessoaJuridica.findAll", query = "SELECT p FROM PessoaJuridica p"),
    @NamedQuery(name = "PessoaJuridica.findById", query = "SELECT p FROM PessoaJuridica p WHERE p.id = :id"),
    @NamedQuery(name = "PessoaJuridica.findByRazaoSocial", query = "SELECT p FROM PessoaJuridica p WHERE p.razaoSocial = :razaoSocial"),
    @NamedQuery(name = "PessoaJuridica.findByNomeFantasia", query = "SELECT p FROM PessoaJuridica p WHERE p.nomeFantasia = :nomeFantasia"),
    @NamedQuery(name = "PessoaJuridica.findByCnpj", query = "SELECT p FROM PessoaJuridica p WHERE p.cnpj = :cnpj")})
public class PessoaJuridica implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Size(max = 80)
    @Column(name = "razao_social")
    private String razaoSocial;
    
    @Size(max = 80)
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "cnpj", unique = true)
    private String cnpj;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoaJuridica")
    private EmpresaLicitante empresaLicitante;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPessoaJuridica")
    private ContatoPessoaJuridica contatoPessoaJuridica;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoaJuridica")
    private InstituicaoLicitadora instituicaoLicitadora;
    
    @Embedded
    private Endereco endereco;

    public PessoaJuridica() {
        endereco = new Endereco();
        contatoPessoaJuridica = new ContatoPessoaJuridica();
    }

    public PessoaJuridica(Long id) {
        this.id = id;
    }

    public PessoaJuridica(Long id, String cnpj) {
        this.id = id;
        this.cnpj = cnpj;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public EmpresaLicitante getEmpresaLicitante() {
        return empresaLicitante;
    }

    public void setEmpresaLicitante(EmpresaLicitante empresaLicitante) {
        this.empresaLicitante = empresaLicitante;
    }

    public ContatoPessoaJuridica getContatoPessoaJuridica() {
        return contatoPessoaJuridica;
    }

    public void setContatoPessoaJuridica(ContatoPessoaJuridica contatoPessoaJuridica) {
        this.contatoPessoaJuridica = contatoPessoaJuridica;
    }

    

    public InstituicaoLicitadora getInstituicaoLicitadora() {
        return instituicaoLicitadora;
    }

    public void setInstituicaoLicitadora(InstituicaoLicitadora instituicaoLicitadora) {
        this.instituicaoLicitadora = instituicaoLicitadora;
    }
    
    public Endereco getEndereco(){
        return this.endereco;
    }
    
    public void setEndereco(Endereco endereco){
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
        if (!(object instanceof PessoaJuridica)) {
            return false;
        }
        PessoaJuridica other = (PessoaJuridica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.PessoaJuridica[ id=" + id + " ]";
    }
    
}
