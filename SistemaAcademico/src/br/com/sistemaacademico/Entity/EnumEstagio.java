package br.com.sistemaacademico.Entity;

/* Enumeration */
public enum EnumEstagio {
    ZERO(null),
    PRIMEIRO("Primeiro"),
    SEGUNDO("Segundo"), 
    TERCEIRO("Terçeiro");
    
    private String enums;
    
    EnumEstagio(String enums){
        this.enums = enums;
    }
    
    public String toString(){
        return enums;
    }
    
}
