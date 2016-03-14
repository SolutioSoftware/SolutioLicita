package br.com.solutiolicita.modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WitaloCarlos
 */
@Entity
@Table(name = "tbl_login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findById", query = "SELECT l FROM Login l WHERE l.id = :id"),
    @NamedQuery(name = "Login.findByUsuario", query = "SELECT l FROM Login l WHERE l.usuario = :usuario"),
    @NamedQuery(name = "Login.buscaPorLogin", query = "SELECT l FROM Login l WHERE l.usuario = :usuario AND l.senha = :senha"),
    @NamedQuery(name = "Login.findBySenha", query = "SELECT l FROM Login l WHERE l.senha = :senha"),
    @NamedQuery(name = "Login.findByLogado", query = "SELECT l FROM Login l WHERE l.logado = :logado")})
public class Login implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario", unique = true)
    private String usuario;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "senha")
    private String senha;
    
    @Column(name = "logado")
    private Boolean logado;
    
    @JoinColumn(name = "id_pregoeiro", referencedColumnName = "id")
    @OneToOne
    private Pregoeiro idPregoeiro;

    public Login() {
    }

    public Login(Long id) {
        this.id = id;
    }

    public Login(Long id, String usuario, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getLogado() {
        return logado;
    }

    public void setLogado(Boolean logado) {
        this.logado = logado;
    }

    public Pregoeiro getIdPregoeiro() {
        return idPregoeiro;
    }

    public void setIdPregoeiro(Pregoeiro idPregoeiro) {
        this.idPregoeiro = idPregoeiro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.solutio.licita.modelo.Login[ id=" + id + " ]";
    }
    
}
