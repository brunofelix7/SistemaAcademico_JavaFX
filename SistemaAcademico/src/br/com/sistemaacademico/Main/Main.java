package br.com.sistemaacademico.Main;

import br.com.sistemaacademico.Conexao.Persistencia;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
        
        //Persistir dados
        Persistencia persistencia = new Persistencia();

        //Executar Interface JavaFX
        launch(args);
        
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        //Abrir interface de autenticação do sistema
        Parent root = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/Autenticacao_View.fxml"));
        Scene scene = new Scene(root);
        root.setId("root");
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
        stage.setTitle("Sistema Acadêmico / JM@cade");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
    
}