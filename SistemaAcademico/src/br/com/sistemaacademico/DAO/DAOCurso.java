package br.com.sistemaacademico.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.sistemaacademico.Entity.Curso;
import br.com.sistemaacademico.Conexao.JDBCConexao;
import static br.com.sistemaacademico.DAO.DAOCurso.getInstance;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Query;

public class DAOCurso {
    
    private static DAOCurso instance; 
    protected EntityManager entityManager;
    
    //Criando uma instancia de conexão
    public static DAOCurso getInstance(){
        if(instance == null){
            instance = new DAOCurso();
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
    
    public DAOCurso(){
        entityManager = getEntityManager();
    }
    
    //1. Cadastrar Curso
    public void cadastrarCurso(Curso curso){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(curso); //merge() -> Usado para objetos que já existem (usar sets nesse caso)
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!
    }
    
    //2. Atualizar Curso
    public Curso alterarCurso(int id){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Curso curso = entityManager.find(Curso.class, id);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!
        return curso;
    }
    
    //3. Ecluir Curso (TableView)
    public void remover(int id){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Curso curso = entityManager.find(Curso.class, id);
        entityManager.remove(curso);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!
    }
    
    
    //4. Listar Cursos
    public List<Curso> listarCurso(){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select curso from Curso curso");
        List<Curso> cursos = query.getResultList();
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!
        return cursos;
    }
    
    //5. Listar Dados de Cursos do Banco para a TabelView
    public ObservableList<Curso> listarTudo(){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select curso from Curso curso");
        ObservableList<Curso> list = FXCollections.observableArrayList(query.getResultList());
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!
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
