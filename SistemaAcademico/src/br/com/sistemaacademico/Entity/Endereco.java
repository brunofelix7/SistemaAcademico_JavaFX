package br.com.sistemaacademico.Entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition= "Integer UNSIGNED")
    private Integer codigo;
    
    @Column(nullable = false, length = 80)
    private String rua;
    
    @Column(nullable = false, columnDefinition = "INT(10) UNSIGNED")
    private int numero;
    
    @Column(columnDefinition = "VARCHAR(150) DEFAULT NULL")
    private String complemento;
    
    @Column(nullable = false, length = 10)
    private String cep;
    
    @Column(nullable = false, length = 45)
    private String bairro;
    
    @Column(nullable = false, length = 45)
    private String cidade;
    
    @Column(nullable = false, columnDefinition = "ENUM('AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RR','RO','RJ','RN','RS','SC','SP','SE','TO')")
    @Enumerated(EnumType.STRING)
    private EnumEstado estado;
    
    @Column(nullable = true, name="codigo_aluno", columnDefinition= "INTEGER UNSIGNED", updatable=false, insertable=false)
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer codigo_aluno;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name= "codigo_aluno", referencedColumnName="codigo")
    private Aluno aluno;
    
    
    
    /*
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="administrador")
    private List<Administrador> administrador;
    */
    
    

    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * @param rua the rua to set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the estado
     */
    public EnumEstado getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EnumEstado estado) {
        this.estado = estado;
    }

    /**
     * @return the aluno
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the codigo_aluno
     */
    public Integer getCodigo_aluno() {
        return codigo_aluno;
    }

    /**
     * @param codigo_aluno the codigo_aluno to set
     */
    public void setCodigo_aluno(Integer codigo_aluno) {
        this.codigo_aluno = codigo_aluno;
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
