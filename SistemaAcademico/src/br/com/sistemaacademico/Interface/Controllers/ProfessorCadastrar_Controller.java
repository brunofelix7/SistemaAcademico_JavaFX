package br.com.sistemaacademico.Interface.Controllers;

import br.com.sistemaacademico.Entity.Professor;
import br.com.sistemaacademico.DAO.DAOProfessor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProfessorCadastrar_Controller implements Initializable {

    @FXML private TextField idNome;
    @FXML private TextField idEmail;
    @FXML private TextField idRg;
    @FXML private TextField idCpf;
    @FXML private TextField idTelefone;
    @FXML private TextField idCelular;
    @FXML private TextField idTitulacao;
    @FXML private TextField idLogin;
    @FXML private PasswordField idSenha;
    @FXML private DatePicker idDataNascimento;
    @FXML private Button idCadastrar;       
    @FXML private Button idCancelar;
    @FXML private Button idTeclado;
    @FXML private Button idLimpar;
    
    String cpf;
    String email;
    String rg;
    String login;
    
    @FXML
    public void limpar(ActionEvent event){
        //LIMPAR CAMPOS
        idNome.clear();
        idEmail.clear();
        idRg.clear();
        idCpf.clear();
        idTelefone.clear();
        idCelular.clear();
        idTitulacao.clear();
        idLogin.clear();
        idSenha.clear();
        idDataNascimento.setValue(null);
    }
                        
    @FXML
    public void cadastrar(ActionEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException{
        if(idNome.getText().isEmpty() || idEmail.getText().isEmpty() || idRg.getText().isEmpty() || idTitulacao.getText().isEmpty() ||
            idCpf.getText().isEmpty() || idTelefone.getText().isEmpty() || idCelular.getText().isEmpty() || 
            idLogin.getText().isEmpty() || idSenha.getText().isEmpty() || idDataNascimento.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
            alert.showAndWait();
        }else{
            try{
                Professor professor = new Professor();
                DAOProfessor daoProfessor = new DAOProfessor();
                professor.setNome(idNome.getText());
                professor.setEmail(idEmail.getText());
                professor.setRg(idRg.getText());
                professor.setCpf(idCpf.getText());
                professor.setTelefone(idTelefone.getText());
                professor.setCelular(idCelular.getText());
                professor.setTitulacao(idTitulacao.getText());
                professor.setLogin(idLogin.getText());
                String senha = daoProfessor.criptografarSenha(idSenha.getText()); //Criptografar senha do professor
                professor.setSenha(senha);
                Date dataMatricula = new Date();
                professor.setDataNascimento(java.sql.Date.valueOf(idDataNascimento.getValue()));
                professor.setDataAdmissao(dataMatricula);
                
                List<Professor> professor2 = daoProfessor.listarProfessor();
                for(Professor prof: professor2){
                    this.cpf = prof.getCpf();
                    this.email = prof.getEmail();
                    this.login = prof.getLogin();
                    this.rg = prof.getRg();
                }if(cpf == null || email == null || login == null || rg == null){
                    daoProfessor.cadastrarProfessor(professor);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cadastrado com Sucesso!");
                    alert.showAndWait();

                    Stage stage = (Stage) idCancelar.getScene().getWindow();
                    stage.close();
                }if(cpf.equals(idCpf.getText()) || email.equals(idEmail.getText()) ||
                login.equals(idLogin.getText()) || rg.equals(idRg.getText())){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Registro já existe!");
                    alert.showAndWait();
                }else{
                    daoProfessor.cadastrarProfessor(professor);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cadastrado com Sucesso!");
                    alert.showAndWait();

                    Stage stage = (Stage) idCancelar.getScene().getWindow();
                    stage.close();
                }
                }catch(NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "O campo Número não pode conter caracteres, \nou ultrapassar mais de 10 números!");
                    alert.showAndWait();
                }catch(NullPointerException e){
                    System.err.println("");
                }
        }
    }
    
    @FXML
    public void cancelar(ActionEvent event){
        //FECHAR JANELA
        Stage stage = (Stage) idCancelar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void abrirTeclado(ActionEvent event){
        try{
            Runtime.getRuntime().exec("cmd /c C:\\Windows\\System32\\osk.exe");
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void initialize(URL Aluno, ResourceBundle resources){

    }    
    
}
