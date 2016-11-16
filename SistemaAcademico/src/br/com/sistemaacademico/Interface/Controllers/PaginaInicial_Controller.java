package br.com.sistemaacademico.Interface.Controllers;

import br.com.sistemaacademico.Conexao.Persistencia;
import br.com.sistemaacademico.DAO.DAOAdministrador;
import java.awt.Desktop;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.io.IOException;

public class PaginaInicial_Controller implements Initializable {
    
    @FXML private MenuItem idSair;
    @FXML private MenuItem idCadastrar2;
    @FXML private MenuItem idDoc;
    @FXML private MenuItem idSobre;
    
    //FECHAR CONEXÃO E SAIR DO SISTEMA
    @FXML
    public void sair(ActionEvent event){
        Persistencia persistencia = new Persistencia();
        persistencia.fecharConeccao();
        System.exit(0);
    }
    
    @FXML
    public void relatorio(ActionEvent event){
        DAOAdministrador daoAdministrador = new DAOAdministrador();
        daoAdministrador.gerarRelatorioPDF();
    }
    
    @FXML
    public void cadastrarAluno(ActionEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException{
        Stage stage = new Stage();
        stage.setTitle("JM@cade - Cadastrar Aluno");
        Parent telaAluno = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/AlunoCadastrar_View.fxml"));
        Scene scene = new Scene(telaAluno);
        scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void cadastrarProfessor(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        stage.setTitle("JM@cade - Cadastrar Professor");
        Parent telaAluno = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/ProfessorCadastrar_View.fxml"));
        Scene scene = new Scene(telaAluno);
        scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void cadastrarDisciplina(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        stage.setTitle("JM@cade - Cadastrar Disciplina");
        Parent telaAluno = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/DisciplinaCadastrar_View.fxml"));
        Scene scene = new Scene(telaAluno);
        scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void cadastrarCurso(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        stage.setTitle("JM@cade - Cadastrar Curso");
        Parent telaAluno = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/CursoCadastrar_View.fxml"));
        Scene scene = new Scene(telaAluno);
        scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
        stage.setScene(scene);
        stage.show();
    }
        
    @FXML
    public void consultarProfessor(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        stage.setTitle("JM@cade - Gerenciamento de Professor");
        Parent telaAluno = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/ProfessorGerenciamento_View.fxml"));
        Scene scene = new Scene(telaAluno);
        scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
        stage.setScene(scene);
        stage.show();
    }
        
    @FXML
    public void gerenciarAluno(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        stage.setTitle("JM@cade - Gerenciamento de Aluno");
        Parent telaAluno = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/AlunoGerenciamento_View.fxml"));
        Scene scene = new Scene(telaAluno);
        scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void gerenciarDisciplina(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        stage.setTitle("JM@cade - Gerenciamento de Disciplina");
        Parent telaAluno = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/DisciplinaGerenciamento_View.fxml"));
        Scene scene = new Scene(telaAluno);
        scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void gerenciarCurso(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        stage.setTitle("JM@cade - Gerenciamento de Curso");
        Parent telaAluno = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/CursoGerenciamento_View.fxml"));
        Scene scene = new Scene(telaAluno);
        scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void abrirDocumentacao(ActionEvent event) throws IOException, URISyntaxException{
        String url = "https://drive.google.com/open?id=0B5WXaAPQXUDmb3pKbFgwNUFSYjg";
        Desktop.getDesktop().browse(new URL(url).toURI());
    }
    
    @FXML
    public void sobre(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "JM@cade\nDesenvolvedores:\n\nBruno Felix\nEmerson Lemos\nJoão Marcus\n\nGit\nhttps://gitlab.com/bruno_felix/UNIPE_P3_ProjetoFINAL.git");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
    }    
    
}
