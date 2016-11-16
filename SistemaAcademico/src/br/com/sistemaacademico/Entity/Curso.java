package br.com.sistemaacademico.Entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition= "INT UNSIGNED")
    private int codigo;
    
    @Column(nullable = false, length = 60)
    private String nome;
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="aluno_curso_prova")
    private List<Prova> prova;
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="aluno_curso_prova")
    private List<Aluno> aluno;

    /*
    public Curso(String nome){
        super();
        this.nome = nome;
    }
    */
    
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
     * @return the prova
     */
    public List<Prova> getProva() {
        return prova;
    }

    /**
     * @param prova the prova to set
     */
    public void setProva(List<Prova> prova) {
        this.prova = prova;
    }

    /**
     * @return the aluno
     */
    public List<Aluno> getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(List<Aluno> aluno) {
        this.aluno = aluno;
    }
    



    
    
    
}
