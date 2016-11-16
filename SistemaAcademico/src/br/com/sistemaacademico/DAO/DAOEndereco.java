package br.com.sistemaacademico.DAO;

import br.com.sistemaacademico.Entity.Endereco;
import static br.com.sistemaacademico.DAO.DAOEndereco.getInstance;
import br.com.sistemaacademico.Entity.Professor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DAOEndereco {
    
    private static DAOEndereco instance; 
    protected EntityManager entityManager;
    
    
    //Criando uma instancia de conexão, Método de Singleton
    public static DAOEndereco getInstance(){
        if(instance == null){
            instance = new DAOEndereco();
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemaacademico");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }
    
    public DAOEndereco(){
        entityManager = getEntityManager();
    }
    
    //1. Cadastrar Endereco
    public void cadastrarEndereco(Endereco endereco){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(endereco);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
    }
    
    //2. Atualizar Endereco
    public Endereco alterarEndereco(int id){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Endereco endereco = entityManager.find(Endereco.class, id);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return endereco;
    }
     
    //3. Ecluir Professor (TableView)
    public void remover(int id){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Endereco endereco = entityManager.find(Endereco.class, id);
        entityManager.remove(endereco);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
    }
    
    //4. Listar Endereco
    public List<Endereco> listarEndereco(){
        getInstance().getEntityManager();
        Endereco end = new Endereco();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select endereco from Endereco endereco " + "INNER JOIN FETCH endereco.professor AS professor");
        List<Endereco> endereco = query.getResultList();
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return endereco;
    }
    
    //4. Listar Endereco
    public List<Endereco> listarEnderecoAluno(){
        getInstance().getEntityManager();
        Endereco end = new Endereco();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select endereco from Endereco endereco " + "INNER JOIN FETCH endereco.aluno AS aluno");
        List<Endereco> endereco = query.getResultList();
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return endereco;
    }

    
    
}
