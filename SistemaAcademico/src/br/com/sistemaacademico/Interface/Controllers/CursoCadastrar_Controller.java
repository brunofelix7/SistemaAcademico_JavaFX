package br.com.sistemaacademico.Interface.Controllers;

import br.com.sistemaacademico.Entity.Curso;
import br.com.sistemaacademico.DAO.DAOCurso;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CursoCadastrar_Controller implements Initializable {

    @FXML private TextField idNome;
    @FXML private Button idCadastrar;       
    @FXML private Button idCancelar;
    @FXML private Button idTeclado;
    @FXML private Button idLimpar;
    
    String nome;
    
    @FXML
    public void limpar(ActionEvent event){
        //LIMPAR CAMPOS
        idNome.clear();
    }
                        
    
    @FXML
    public void cadastrar(ActionEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException{
        if(idNome.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
            alert.showAndWait();
        }else{
            try{
                Curso curso = new Curso();
                DAOCurso daoCurso = new DAOCurso();
                curso.setNome(idNome.getText());
                
                List<Curso> curso2 = daoCurso.listarCurso();
                for(Curso cursos: curso2){
                    this.nome = cursos.getNome();
                }if(nome == null){
                    daoCurso.cadastrarCurso(curso);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cadastrado com Sucesso!");
                    alert.showAndWait();

                    //LIMPAR CAMPOS APÓS O CADASTRO
                    idNome.clear();
                }if(nome.equals(idNome.getText())){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Registro já existe!");
                    alert.showAndWait();
                }else{
                    daoCurso.cadastrarCurso(curso);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cadastrado com Sucesso!");
                    alert.showAndWait();

                    //LIMPAR CAMPOS APÓS O CADASTRO
                    idNome.clear();
                }
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
