package br.com.sistemaacademico.Entity;

/* Enumeration */
public enum EnumPeriodo {
    P0(null),
    P1("P1"), 
    P2("P2"), 
    P3("P3"), 
    P4("P4"), 
    P5("P5"), 
    P6("P6"), 
    P7("P7"), 
    P8("P8"), 
    P9("P9"), 
    P10("P10");
    
    private String enums;
    
    EnumPeriodo(String enums){
        this.enums = enums;
    }

    public String toString(){
        return enums;
    }
    
}
