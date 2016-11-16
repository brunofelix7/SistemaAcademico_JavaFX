package br.com.sistemaacademico.Entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "disciplina")
public class Disciplina {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition= "INT UNSIGNED")
    private int codigo;
    
    @Column(nullable = false, length = 60)
    private String nome;
    
    @Column(nullable = false, columnDefinition = "ENUM('P1','P2','P3','P4','P5','P6','P7','P8','P9','P10')")
    @Enumerated(EnumType.STRING)
    private EnumPeriodo periodo;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Professor professor;
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="curso_disciplina", joinColumns=@JoinColumn(name="codigo_disciplina", referencedColumnName="codigo"),
               inverseJoinColumns=@JoinColumn(name="codigo_curso", referencedColumnName="codigo"))
    private List<Curso> curso;
    
    
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
     * @return the periodo
     */
    public EnumPeriodo getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(EnumPeriodo periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the professor
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
     * @return the curso
     */
    public List<Curso> getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(List<Curso> curso) {
        this.curso = curso;
    }


    
}
