package br.com.sistemaacademico.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.sistemaacademico.Entity.Aluno;
import static br.com.sistemaacademico.DAO.DAOAluno.getInstance;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Query;

public class DAOAluno {
    
    private static DAOAluno instance; 
    protected EntityManager entityManager;

    
    public static DAOAluno getInstance(){
        if(instance == null){
            instance = new DAOAluno();
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

    public DAOAluno(){
        entityManager = getEntityManager();
    }
    
    //1. Cadastrar Aluno     
    public void cadastrarAluno(Aluno aluno) {
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(aluno);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
    }
    
    //2. Atualizar Aluno
    public Aluno alterarAluno(int id){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Aluno aluno = entityManager.find(Aluno.class, id);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return aluno;
    }
    
    //3. Ecluir Aluno (TableView)
    public void remover(int id){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Aluno aluno = entityManager.find(Aluno.class, id);
        entityManager.remove(aluno);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
    }
    
    //4. Listar Alunos
    public List<Aluno> listarAluno(){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select aluno from Aluno aluno");
        List<Aluno> aluno = query.getResultList();
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return aluno;
    }
    
    //5. Listar Dados de Alunos do Banco para a TabelView
    public ObservableList<Aluno> listarTudo(){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select aluno from Aluno aluno");
        ObservableList<Aluno> list = FXCollections.observableArrayList(query.getResultList());
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
