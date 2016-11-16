package br.com.sistemaacademico.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.sistemaacademico.Entity.Professor;
import static br.com.sistemaacademico.DAO.DAOProfessor.getInstance;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Query;

public class DAOProfessor {
    
    private static DAOProfessor instance; 
    protected EntityManager entityManager;
    
    
    public static DAOProfessor getInstance(){
        if(instance == null){
            instance = new DAOProfessor();
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
    
    public DAOProfessor(){
        entityManager = getEntityManager();
    }
    
    
    //1. Cadastrar Professor
    public void cadastrarProfessor(Professor professor){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(professor);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
    }
    
    //2. Atualizar Professor
    public Professor alterarProfessor(int id){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Professor professor = entityManager.find(Professor.class, id);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return professor;
    }
    
    //3. Ecluir Professor (TableView)
    public void remover(int id){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Professor professor = entityManager.find(Professor.class, id);
        entityManager.remove(professor);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
    }
    
    //4. Listar Professores
    public List<Professor> listarProfessor(){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select professor from Professor professor");
        List<Professor> professor = query.getResultList();
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return professor;
    }
    
    //5. Listar Dados de Professores do Banco para a TabelView
    public ObservableList<Professor> listarTudo(){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select professor from Professor professor");
        ObservableList<Professor> list = FXCollections.observableArrayList(query.getResultList());
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return list;
    }
    
    //6. Gerar Senha Criptografada com SHA2 de 256 bits
    public String criptografarSenha(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256"); //MD5
        byte vetor[] = messageDigest.digest(password.getBytes("UTF-8"));

        StringBuilder hashString = new StringBuilder();
        for (byte b : vetor) {
            hashString.append(String.format("%02X", 0xFF & b));
        }
        String senhaCriptografada = hashString.toString();
        return senhaCriptografada;
    }
    
}
