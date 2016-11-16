package br.com.sistemaacademico.Interface.Controllers;

import br.com.sistemaacademico.Entity.EnumPeriodo;
import br.com.sistemaacademico.Entity.Disciplina;
import br.com.sistemaacademico.DAO.DAODisciplina;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DisciplinaCadastrar_Controller implements Initializable {

    @FXML private ComboBox<EnumPeriodo> idPeriodo;
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
    public void cadastrar(ActionEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException, IOException{
        if(idNome.getText().isEmpty() || idPeriodo.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
            alert.showAndWait();
        }else{
            try{
                Disciplina disciplina = new Disciplina();
                DAODisciplina daoDisciplina = new DAODisciplina();
                disciplina.setNome(idNome.getText());
                disciplina.setPeriodo(idPeriodo.getValue());
                
                List<Disciplina> disciplina2 = daoDisciplina.listarDisciplina();
                for(Disciplina disc: disciplina2){
                    this.nome = disc.getNome();
                }
                if(nome == null){
                    daoDisciplina.cadastrarDisciplina(disciplina);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cadastrado com Sucesso!");
                    alert.showAndWait();
                    
                    Alert alert2 = new Alert(Alert.AlertType.WARNING, "Por favor, cadastre um professor para essa disciplina...");
                    alert2.showAndWait();
                    
                    Stage stage = (Stage) idCancelar.getScene().getWindow();
                    stage.close();
                    
                    Parent telaProfessor = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/ProfessorCadastrar_View.fxml"));
                    Scene scene = new Scene(telaProfessor);
                    scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
                    Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage2.setTitle("JM@cade - Cadastro de Professor");
                    stage2.setScene(scene);
                    stage2.show();
                    
                    
                }else{
                    daoDisciplina.cadastrarDisciplina(disciplina);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cadastrado com Sucesso!");
                    alert.showAndWait();

                    Alert alert2 = new Alert(Alert.AlertType.WARNING, "Por favor, cadastre um professor para essa disciplina...");
                    alert2.showAndWait();
                    
                    Stage stage = (Stage) idCancelar.getScene().getWindow();
                    stage.close();
                    
                    Parent telaProfessor = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/ProfessorCadastrar_View.fxml"));
                    Scene scene = new Scene(telaProfessor);
                    scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
                    Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage2.setTitle("JM@cade - Cadastro de Professor");
                    stage2.setScene(scene);
                    stage2.show();
                    
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
        ObservableList<EnumPeriodo> periodos = FXCollections.observableArrayList(EnumPeriodo.values());
        idPeriodo.setItems(periodos);
    }    
    
}
