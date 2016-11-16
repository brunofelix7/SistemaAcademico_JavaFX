package br.com.sistemaacademico.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.sistemaacademico.Entity.Administrador;
import static br.com.sistemaacademico.DAO.DAOAdministrador.getInstance;
import br.com.sistemaacademico.Entity.Aluno;
import br.com.sistemaacademico.Entity.Curso;
import br.com.sistemaacademico.Entity.Disciplina;
import br.com.sistemaacademico.Entity.Professor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javax.persistence.Query;

public class DAOAdministrador {
    
    private static DAOAdministrador instance; 
    protected EntityManager entityManager;
    private String emailInvalido;
    private String emailValido;
    
    public static DAOAdministrador getInstance(){
        if(instance == null){
            instance = new DAOAdministrador();
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
    
    public DAOAdministrador(){
        entityManager = getEntityManager();
    }
    

    //1. Usado para fazer o UPDATE no banco da nova senha criptografada do administrador, 
    //caso ele execute a operação de recuperar senha
    public void cadastrarAdmin(Administrador admin) throws ParseException, NoSuchAlgorithmException, UnsupportedEncodingException{
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(admin);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
    }
    
    //2. Faz o busca pela Id para excutar uma instrução de UPDATE junto com o método cadastrarAdmin()
    //no banco da nova senha criptografada do administrador, caso ele execute a operação de recuperar senha
    public Administrador atualizarSenha(int id){
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Administrador admin = entityManager.find(Administrador.class, id);
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return admin;
    }
    
    //3. Gerar Senha Criptografada com SHA2 de 256 bits
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

    //4. Autenticar Administrador no Sistema
    public List<Administrador> autenticar(String login, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{ //FAZER LEITURA NA INTERFACE
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select administrador from Administrador administrador");
        List<Administrador> administrador = query.getResultList();
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return administrador;
    }
    
    //5. Recuperar senha do administrador (JavaMail)
    public List<Administrador> recuperarSenha(String emailInformado) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select administrador from Administrador administrador");
        List<Administrador> administrador = query.getResultList();
        entityManager.getTransaction().commit();
        //entityManager.close();    Ocorrendo erro!?
        return administrador;
    }
    
    //6. Gerar Relatório em PDF
    public void gerarRelatorioPDF(){
        Alert alert0 = new Alert(Alert.AlertType.WARNING, "Solicitação em andamento, por favor aguarde...");
        alert0.showAndWait();
        
        List<String> cursoNome = new ArrayList();
        List<String> disciplinaNome = new ArrayList();
        List<String> disciplinaPeriodo = new ArrayList();
        List<String> professorNome = new ArrayList();
        List<String> alunoNome = new ArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dataHora = simpleDateFormat.format(date);
        
        //Listar dados de Cursos
        DAOCurso daoCurso = new DAOCurso();
        List<Curso> curso = daoCurso.listarCurso();
        for(Curso cursos: curso){
            cursoNome.add(cursos.getNome());
        }
        
        //Listar dados de Disciplinas
        DAODisciplina daoDisciplina = new DAODisciplina();
        List<Disciplina> disciplina = daoDisciplina.listarDisciplina();
        for(Disciplina disciplinas: disciplina){
            disciplinaNome.add(disciplinas.getNome());
            disciplinaPeriodo.add(disciplinas.getPeriodo().toString());
        }
        
        //Listar dados de Professores
        DAOProfessor daoProfessor = new DAOProfessor();
        List<Professor> professor = daoProfessor.listarProfessor();
        for(Professor professores: professor){
            professorNome.add(professores.getNome());
        }
        
        //Listar dados de Alunos
        DAOAluno daoAluno = new DAOAluno();
        List<Aluno> aluno = daoAluno.listarAluno();
        for(Aluno alunos: aluno){
            alunoNome.add(alunos.getNome());
        }
        
        //Criação do documento
        Document document = new Document();

        try{
            PdfWriter.getInstance(document, new FileOutputStream("Relatorio.pdf"));
            document.open();

            //Adicionando um parágrafo no documento
            com.itextpdf.text.Font fontbold = FontFactory.getFont("Arial", 12, Font.BOLD);
            document.add(new Paragraph("Relatório Geral - " + dataHora, fontbold));
            document.add(new Paragraph("Sistema Acadêmico - JM@cade\n", fontbold));
            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------\n\n", fontbold));
            document.add(new Paragraph("Relação de Cursos:\n", fontbold));
            for(int i = 0; i < cursoNome.size(); i++){
                document.add(new Paragraph(cursoNome.get(i)));
            }
            document.add(new Paragraph("\nRelação de Disciplinas:\n", fontbold));
            for(int i = 0; i < disciplinaNome.size(); i++){
                document.add(new Paragraph(disciplinaNome.get(i) + " - " + disciplinaPeriodo.get(i)));
            }
            document.add(new Paragraph("\nRelação de Professores:\n", fontbold));
            for(int i = 0; i < professorNome.size(); i++){
                document.add(new Paragraph(professorNome.get(i)));
            }
            document.add(new Paragraph("\nRelação de Alunos:\n", fontbold));
            for(int i = 0; i < alunoNome.size(); i++){
                document.add(new Paragraph(alunoNome.get(i)));
            }

        }catch(DocumentException de) {
            System.err.println(de.getMessage());
        }catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }finally{
            document.close();
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Relatório gerado com sucesso, deseja abrí-lo agora?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
            try{
            Runtime.getRuntime().exec("cmd /c Relatorio.pdf");
            }catch(IOException e){
                Alert alert2 = new Alert(Alert.AlertType.ERROR, "Erro ao abrir o arquivo!");
                alert2.showAndWait();
            }
        }if(alert.getResult() == ButtonType.NO){
            alert.close();
        }
    }

    
    /**
     * @return the emailInvalido
     */
    public String getEmailInvalido() {
        return emailInvalido;
    }

    /**
     * @param emailInvalido the emailInvalido to set
     */
    public void setEmailInvalido(String emailInvalido) {
        this.emailInvalido = emailInvalido;
    }

    /**
     * @return the emailValido
     */
    public String getEmailValido() {
        return emailValido;
    }

    /**
     * @param emailValido the emailValido to set
     */
    public void setEmailValido(String emailValido) {
        this.emailValido = emailValido;
    }

    
}
