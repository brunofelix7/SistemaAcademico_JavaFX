package br.com.sistemaacademico.Interface.Controllers;

import br.com.sistemaacademico.Entity.Administrador;
import br.com.sistemaacademico.DAO.DAOAdministrador;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Autenticacao_Controller implements Initializable {

    
    @FXML private Button onClick;
    @FXML private TextField idLogin;
    @FXML private PasswordField idSenha;
    @FXML private Hyperlink idRecuperarSenha;
    @FXML private Label idLabelError;
    @FXML private Label idWelcome;
    @FXML private Label idUsername;
    @FXML private Label idPassword;
    
    
    //AUTENTICAR USUÁRIO
    @FXML
    public void autenticar(ActionEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, InterruptedException{
        String login = idLogin.getText();
        String password = idSenha.getText();
        
        if(login.isEmpty() || password.isEmpty()){
                this.idLabelError.setText("Preencha os campos");
        }else{
            DAOAdministrador daoAdministrador = new DAOAdministrador();
            //Criptografa a senha digitada
            String senhaCriptografada = daoAdministrador.criptografarSenha(password);

            //Chama o método autenticar passando como argumento o login e a senha(criptografada) informados
            List<Administrador> administrador = daoAdministrador.autenticar(login, senhaCriptografada);

            //Faz a comparação da senha criptografada com a que está lá no banco de dados
            for(Administrador admin : administrador){
                String nome = admin.getNome();      //Traz o nome que está la no banco e armazena em uma String
                String usuario = admin.getLogin();  //Traz o login que está la no banco e armazena em uma String
                String senha = admin.getSenha();   //Traz a senha criptografada que está lá no banco e armazena em uma String

                //Validação de login e senha
                if(senha.equals(senhaCriptografada) && usuario.equals(login)){
                    this.idLabelError.setText(" ");
                    Alert alert = new Alert(AlertType.INFORMATION, "Olá Senhor, " + nome); //Seta o nome que está cadastrada no banco de dados
                    alert.showAndWait();

                    //ABRIR TELA PaginaInicial
                    //aguarda 3 segundos antes de entrar Thread.sleep(3000) ou TimeUnit.SECONDS.sleep(5)
                    Parent telaInicial = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/PaginaInicial_View.fxml"));
                    Scene scene = new Scene(telaInicial);
                    scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
                    telaInicial.setId("telaInicial");
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("JM@cade - Pagina Inicial");
                    stage.setScene(scene);
                    stage.show();
                    break;

                }else{
                    this.idLabelError.setText("Usuário/Senha inválidos");
                    break;
                }
            }
        }
            
    }
    
    
    //RECUPERAR SENHA #1
    @FXML
    private void recuperarSenha(ActionEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, URISyntaxException{
        //ABRIR TELA RecuperarSenha
        Stage stage = new Stage();
        stage.setTitle("JM@cade - Recuperação de Senha");
        Parent telaRecuperarSenha = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/RecuperarSenha_View.fxml"));
        Scene scene = new Scene(telaRecuperarSenha);
        scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
        stage.setScene(scene);
        stage.show();
        /*
        Parent telaRecuperarSenha = FXMLLoader.load(getClass().getResource("RecuperarSenha.fxml"));
        Scene scene = new Scene(telaRecuperarSenha);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();*/
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //ABRIR TELA RecuperarSenha
    }    
    
}
