package br.com.sistemaacademico.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.sistemaacademico.Entity.Disciplina;
import br.com.sistemaacademico.Conexao.JDBCConexao;
import static br.com.sistemaacademico.DAO.DAODisciplina.getInstance;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Query;

public class DAODisciplina {
    
    private static DAODisciplina instance; 
    protected EntityManager entityManager;
    
    /*
    *Criando uma instancia de conexão, Método de Singleton
    */
    public static DAODisciplina getInstance(){
        if(instance == null){
            instance = new DAODisciplina();
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
    
    public DAODisciplina(){
        entityManager = getEntityManager();
    }
    
    //1. Cadastrar Disciplina   
    public void cadastrarDisciplina(Disciplina disciplina){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(disciplina);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
    }
    
    //2. Atualizar Disciplina
    public Disciplina alterarDisciplina(int id){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Disciplina disciplina = entityManager.find(Disciplina.class, id);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return disciplina;
    }
    
    //3. Ecluir Disciplina (TableView)
    public void remover(int id){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Disciplina disciplina = entityManager.find(Disciplina.class, id);
        entityManager.remove(disciplina);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
    }
    
    //4. Listar Disciplinas
    public List<Disciplina> listarDisciplina(){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select disciplina from Disciplina disciplina");
        List<Disciplina> disciplina = query.getResultList();
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return disciplina;
    }
    
//5. Listar Dados de Disciplinas do Banco para a TabelView
    public ObservableList<Disciplina> listarTudo(){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select disciplina from Disciplina disciplina");
        ObservableList<Disciplina> list = FXCollections.observableArrayList(query.getResultList());
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
