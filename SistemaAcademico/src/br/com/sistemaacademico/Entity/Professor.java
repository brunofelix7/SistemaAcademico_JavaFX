package br.com.sistemaacademico.Entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "professor")
public class Professor {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition= "Integer UNSIGNED")
    private Integer codigo;
    
    @Column (nullable = false, length = 45, unique = true)
    private String login;
    
    @Column (nullable = false, length = 64)
    private String senha;
    
    @Column (nullable = false, length = 80)
    private String nome;
    
    @Column (nullable = false, length = 60, unique = true)
    private String email;
    
    @Column (nullable = false, length = 11, unique = true)
    private String rg;
    
    @Column (nullable = false, length = 14, unique = true)
    private String cpf;
    
    @Column (nullable = false, length = 20)
    private String telefone;
    
    @Column (nullable = false, length = 20)
    private String celular;
    
    @Column (nullable = false, length = 60)
    private String titulacao;
    
    @Column (nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAdmissao;
    
    @Column (nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    


    
    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the titulacao
     */
    public String getTitulacao() {
        return titulacao;
    }

    /**
     * @param titulacao the titulacao to set
     */
    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    /**
     * @return the dataAdmissao
     */
    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    /**
     * @param dataAdmissao the dataAdmissao to set
     */
    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }


    
}
