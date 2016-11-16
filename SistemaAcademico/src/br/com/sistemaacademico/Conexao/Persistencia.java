package br.com.sistemaacademico.Conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Persistencia {
    
    private static Persistencia instance; 
    public EntityManager entityManager;

    //Criando uma instancia de conexão, Método de Singleton
    public static Persistencia getInstance(){
        if(instance == null){
            instance = new Persistencia();
        }
        return instance;
    }
    
    public EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemaacademico");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }
    
    public Persistencia(){
        entityManager = getEntityManager();
    }
    
    
    public void fecharConeccao(){
        entityManager.close();
    }
    
    
}
