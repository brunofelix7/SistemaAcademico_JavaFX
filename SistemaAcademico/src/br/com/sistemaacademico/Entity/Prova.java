package br.com.sistemaacademico.Entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "prova")
public class Prova {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition= "INT UNSIGNED")
    private int codigo;
    
    @Column(nullable = false, columnDefinition = "DOUBLE UNSIGNED")
    private double nota;
    
    @Column(nullable = false, columnDefinition = "DOUBLE UNSIGNED")
    private double media;
    
    @Column(nullable = false, columnDefinition = "ENUM('Primeiro','Segundo','Terçeiro')")
    @Enumerated(EnumType.STRING)
    private EnumEstagio estagio;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataProva;
    
    
    public void calcularMedia(){
        
    }
    
    public void gerarRelatorioGrafico(){
    
    }
    

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
     * @return the nota
     */
    public double getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(double nota) {
        this.nota = nota;
    }

    /**
     * @return the media
     */
    public double getMedia() {
        return media;
    }

    /**
     * @param media the media to set
     */
    public void setMedia(double media) {
        this.media = media;
    }

    /**
     * @return the estagio
     */
    public EnumEstagio getEstagio() {
        return estagio;
    }

    /**
     * @param estagio the estagio to set
     */
    public void setEstagio(EnumEstagio estagio) {
        this.estagio = estagio;
    }

    /**
     * @return the dataProva
     */
    public Date getDataProva() {
        return dataProva;
    }

    /**
     * @param dataProva the dataProva to set
     */
    public void setDataProva(Date dataProva) {
        this.dataProva = dataProva;
    }
    
    
    
}
