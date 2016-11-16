package br.com.sistemaacademico.Interface.Controllers;

import br.com.sistemaacademico.Entity.Endereco;
import br.com.sistemaacademico.Entity.EnumEstado;
import br.com.sistemaacademico.Entity.Aluno;
import br.com.sistemaacademico.DAO.DAOEndereco;
import br.com.sistemaacademico.DAO.DAOAluno;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlunoCadastrar_Controller implements Initializable {

    @FXML private ComboBox<EnumEstado> idEstado;
    @FXML private TextField idNome;
    @FXML private TextField idEmail;
    @FXML private TextField idRg;
    @FXML private TextField idCpf;
    @FXML private TextField idTelefone;
    @FXML private TextField idCelular;
    @FXML private TextField idLogin;
    @FXML private PasswordField idSenha;
    @FXML private DatePicker idDataNascimento;
    @FXML private TextField idRua;
    @FXML private TextField idNumero;
    @FXML private TextField idComplemento;
    @FXML private TextField idCep;
    @FXML private TextField idBairro;
    @FXML private TextField idCidade;
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
        idLogin.clear();
        idSenha.clear();
        idDataNascimento.setValue(null);
        idRua.clear();
        idNumero.clear();
        idComplemento.clear();
        idCep.clear();
        idBairro.clear();
        idCidade.clear();
    }
                        
    
    @FXML
    public void cadastrar(ActionEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException{
        if(idNome.getText().isEmpty() || idEmail.getText().isEmpty() || idRg.getText().isEmpty() || 
            idCpf.getText().isEmpty() || idTelefone.getText().isEmpty() || idCelular.getText().isEmpty() || 
            idLogin.getText().isEmpty() || idSenha.getText().isEmpty() || idDataNascimento.getValue() == null || 
            idRua.getText().isEmpty() || idNumero.getText().isEmpty() || idCep.getText().isEmpty() || 
            idBairro.getText().isEmpty() || idCidade.getText().isEmpty() || idEstado.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
            alert.showAndWait();
        }else{
            try{
                Aluno aluno = new Aluno();
                DAOAluno daoAluno = new DAOAluno();
                aluno.setNome(idNome.getText());
                aluno.setEmail(idEmail.getText());
                aluno.setRg(idRg.getText());
                aluno.setCpf(idCpf.getText());
                aluno.setTelefone(idTelefone.getText());
                aluno.setCelular(idCelular.getText());
                aluno.setLogin(idLogin.getText());
                String senha = daoAluno.criptografarSenha(idSenha.getText()); //Criptografar senha do aluno
                aluno.setSenha(senha);
                Date dataMatricula = new Date();
                aluno.setDataNascimento(java.sql.Date.valueOf(idDataNascimento.getValue()));
                aluno.setDataMatricula(dataMatricula);
                
                Endereco endereco = new Endereco();
                DAOEndereco daoEndereco = new DAOEndereco();
                endereco.setRua(idRua.getText());
                endereco.setNumero(Integer.parseInt(idNumero.getText()));
                endereco.setComplemento(idComplemento.getText());
                endereco.setCep(idCep.getText());
                endereco.setBairro(idBairro.getText());
                endereco.setCidade(idCidade.getText());
                endereco.setEstado(idEstado.getValue());
                
                
                List<Aluno> aluno2 = daoAluno.listarAluno();
                for(Aluno alunos: aluno2){
                    this.cpf = alunos.getCpf();
                    this.email = alunos.getEmail();
                    this.login = alunos.getLogin();
                    this.rg = alunos.getRg();
                }
                if(cpf == null || email == null || login == null || rg == null){
                    daoEndereco.cadastrarEndereco(endereco);
                    daoAluno.cadastrarAluno(aluno);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cadastrado com Sucesso!");
                    alert.showAndWait();
                    
                    //LIMPAR CAMPOS APÓS O CADASTRO
                    idNome.clear();
                    idEmail.clear();
                    idRg.clear();
                    idCpf.clear();
                    idTelefone.clear();
                    idCelular.clear();
                    idLogin.clear();
                    idSenha.clear();
                    idDataNascimento.setValue(null);
                    idRua.clear();
                    idNumero.clear();
                    idComplemento.clear();
                    idCep.clear();
                    idBairro.clear();
                    idCidade.clear();
                }if(cpf.equals(idCpf.getText()) || email.equals(idEmail.getText()) || 
                login.equals(idLogin.getText()) || rg.equals(idRg.getText())){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Registro já existe!");
                    alert.showAndWait();
                }else{
                    daoEndereco.cadastrarEndereco(endereco);
                    daoAluno.cadastrarAluno(aluno);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cadastrado com Sucesso!");
                    alert.showAndWait();

                    //LIMPAR CAMPOS APÓS O CADASTRO
                    idNome.clear();
                    idEmail.clear();
                    idRg.clear();
                    idCpf.clear();
                    idTelefone.clear();
                    idCelular.clear();
                    idLogin.clear();
                    idSenha.clear();
                    idDataNascimento.setValue(null);
                    idRua.clear();
                    idNumero.clear();
                    idComplemento.clear();
                    idCep.clear();
                    idBairro.clear();
                    idCidade.clear();
                }
            }catch(NumberFormatException e){ //NumberFormatException
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
        ObservableList<EnumEstado> estados = FXCollections.observableArrayList(EnumEstado.values());
        idEstado.setItems(estados);
    }    
    
}
