package br.com.sistemaacademico.Interface.Controllers;

import br.com.sistemaacademico.Entity.Administrador;
import br.com.sistemaacademico.DAO.DAOAdministrador;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperarSenha_Controller implements Initializable {
    
    @FXML private TextField idEmail;
    @FXML private Label idLabelErrorEmail;
    @FXML private Button idCancelar;
   
    
    //RECUPERAR SENHA #2
    @FXML
    private void verificarEmail(ActionEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, ParseException{
        String email = idEmail.getText();
        if(email.isEmpty()){
            idLabelErrorEmail.setText("Preencha o campo");
        }else{
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Solicitação em andamento, por favor aguarde...");
            alert2.showAndWait();
            
                    
            DAOAdministrador daoAdministrador = new DAOAdministrador();
            List<Administrador> administrador = daoAdministrador.recuperarSenha(email);

            //Validar email
            for (Administrador admin : administrador){
                String emailValidar = admin.getEmail();
                if(emailValidar.equals(email)){
                    
                    //Iniciar Procedimento do JavaMail #1
                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "465");
                    props.put("mail.smtp.starttls.enable", "true"); 
                    props.put("mail.smtp.socketFactory.port", "465");
                    props.put("mail.smtp.socketFactory.fallback", "false");  
                    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

                    Session session = Session.getDefaultInstance(props, 
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication(){
                                return new PasswordAuthentication("seuemail@gmail.com", "suasenha"); //Aqui estava meu e-mail e senha 
                            }
                        }
                    );

                    //Ativar Debug
                    session.setDebug(true);

                    //Gerar nova senha aleatoriamento
                    Random random = new Random();
                    String newPassword = new BigInteger(32, random).toString(32);

                    //Iniciar Procedimento do JavaMail #2
                    try{
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress("bruno.felix.gemadv@gmail.com")); //Remetente
                        Address[] toUser = InternetAddress.parse("bruno.felix10@hotmail.com"); //Destinatários
                        message.setRecipients(Message.RecipientType.TO, toUser);
                        message.setSubject("SUPORTE: Recuperação de Senha"); //Assunto
                        message.setText("Olá, \n\nVocê solicitou uma nova senha, por favor anote: \n\n" + ":::" + newPassword + ":::" + "\n\nObrigado!!" ); //Conteúdo
                        Transport.send(message); //Enviar mensagem criada

                        //Criptografa senha gerada com SHA2-256bits
                        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256"); //MD5
                        byte vetor[] = messageDigest.digest(newPassword.getBytes("UTF-8"));
                        StringBuilder hashString = new StringBuilder();
                        for (byte b : vetor) {
                            hashString.append(String.format("%02X", 0xFF & b));
                        }
                        String senhaCriptografada = hashString.toString();

                        //Atualizar senha criptografada gerada no banco de dados
                        Administrador admin2 = daoAdministrador.atualizarSenha(1);
                        admin2.setSenha(senhaCriptografada);
                        daoAdministrador.cadastrarAdmin(admin2);

                        idLabelErrorEmail.setText(" ");
                        daoAdministrador.setEmailValido("Nova senha encaminhada com sucesso!");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, daoAdministrador.getEmailValido());
                        alert.showAndWait();

                        //Fecha a Janela
                        Stage stage = (Stage) idCancelar.getScene().getWindow();
                        stage.close();
                        break;

                    }catch(Exception e){
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Erro de conexão!");
                        alert.showAndWait();
                        break;
                    }
                }else{
                    daoAdministrador.setEmailInvalido("Email inválido");
                    idLabelErrorEmail.setText(daoAdministrador.getEmailInvalido());
                    break;
                }

            }
            
    }
        
}

    @FXML
    public void cancelar(ActionEvent event) throws IOException{
        //FECHAR TELA RecuperarSenha
        Stage stage = (Stage) idCancelar.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
    }    
    
}
