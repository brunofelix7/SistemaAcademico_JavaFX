package br.com.sistemaacademico.Interface.Controllers;

import br.com.sistemaacademico.DAO.DAOEndereco;
import br.com.sistemaacademico.Entity.Aluno;
import br.com.sistemaacademico.DAO.DAOAluno;
import br.com.sistemaacademico.Entity.Endereco;
import br.com.sistemaacademico.Entity.EnumEstado;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AlunoGerenciamento_Controller implements Initializable {

    @FXML private Button idEditar;
    @FXML private Button idExcluir;
    @FXML private Button idFechar;
    @FXML private Button idLimpar;
    @FXML private Button idVisualizar;
    @FXML public TableView<Aluno> idTable;
    @FXML private TableColumn nome;
    @FXML private TableColumn email;
    @FXML private TableColumn data_matricula;
    @FXML private TableColumn celular;
    @FXML private TableColumn telefone;
    @FXML private TableColumn rg;
    @FXML private TableColumn cpf;
    @FXML private TableColumn dataAdmissao;
    @FXML private TableColumn dataNascimento;
    @FXML private AnchorPane idAnchorPaneGerenciar;
    @FXML private GridPane idGrid;

    @FXML private AnchorPane idAnchorPane;
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
    @FXML private Button idAtualizar;       
    @FXML private Button idCancelar;
    @FXML private Button idTeclado;
    @FXML private Button idVoltar;
    @FXML private Button idCadastrar;
    @FXML private Button idRefresh;
    
    
    
    public List list = null;
    public List array = null;
    
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
    public void atualizar(ActionEvent event){
        //Operação utilizada mais abaixo
    }
    
    @FXML
    public void cadastrar(ActionEvent event) throws IOException{
        //ABRIR TELA DE CADASTRO DE PROFESSOR
        Stage stage = new Stage();
        stage.setTitle("JM@cade - Cadastrar Aluno");
        Parent telaAluno = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/AlunoCadastrar_View.fxml"));
        Scene scene = new Scene(telaAluno);
        scene.getStylesheets().add("/br/com/sistemaacademico/Interface/Controllers/Estilo.css");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void cancelar(ActionEvent event){
        //Desabilita a tela de edição
        idAnchorPane.setVisible(false);
        
        //Habilita novamente a tela de gerenciamento
        idGrid.setVisible(true);
        idFechar.setVisible(true);
        idEditar.setVisible(true);
        idExcluir.setVisible(true);
        idVisualizar.setVisible(true);
        idTable.setVisible(true);
        idCadastrar.setVisible(true);
        idRefresh.setVisible(true);
    }
    
    @FXML
    public void abrirTeclado(ActionEvent event){
        //Abre o teclado virtual do windows
        try{
            Runtime.getRuntime().exec("cmd /c C:\\Windows\\System32\\osk.exe");
        }catch(IOException e){
            Alert alert2 = new Alert(AlertType.WARNING, "Erro ao executar o processo!");
            alert2.showAndWait();
        }
        
    }
    
    @FXML
    public void fechar(ActionEvent event){
        //Fecha a janela de gerenciamento
        Stage stage = (Stage) idFechar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void voltar(ActionEvent event){
        //Desabilita a tela de visualização
        idAnchorPane.setVisible(false);
        
        //Habilita novamente a tela de gerenciamento
        idGrid.setVisible(true);
        idFechar.setVisible(true);
        idEditar.setVisible(true);
        idExcluir.setVisible(true);
        idVisualizar.setVisible(true);
        idTable.setVisible(true);
        idCadastrar.setVisible(true);
        idRefresh.setVisible(true);
        idVoltar.setVisible(false);
    }
    
    @FXML
    public void visualizar(ActionEvent event){
        try{
            //Inicializa a operação de visualização do dado selecionado na tabela
            TablePosition pos = idTable.getSelectionModel().getSelectedCells().get(0);
            int getRow = pos.getRow();              //Pega o índice da linha selecionada

            list = new ArrayList();                 //Array que vai armazenar as chaves primárias do banco

            DAOAluno daoAluno = new DAOAluno();
            List<Aluno> aluno = daoAluno.listarAluno();
            for(Aluno alunos: aluno){
                getRow = alunos.getCodigo();          //Faz as linhas da tabela serem iguais as chaves primárias do banco
                list.add(getRow);                   //Adiciona no Array as chaves primárias
            }

            //Obtem um elemento pela id no banco, no caso, o valor do índice que eu selecionar na tabela vai representar essa id
            int row = pos.getRow();  
            Aluno alunos = daoAluno.alterarAluno((int) list.get(row));

            //Obtém os endereços referentes aos professores
            DAOEndereco daoEndereco = new DAOEndereco();
            List<Endereco> endereco = daoEndereco.listarEnderecoAluno();
            for(Endereco end: endereco){
                getRow = end.getCodigo_aluno();
                list.add(getRow);              
            }
            Endereco end = daoEndereco.alterarEndereco((int) list.get(row));

            //Esconde o menu de gerenciamento
            idGrid.setVisible(false);
            idFechar.setVisible(false);
            idEditar.setVisible(false);
            idExcluir.setVisible(false);
            idTable.setVisible(false);
            idTeclado.setVisible(false);
            idVisualizar.setVisible(false);
            idCancelar.setVisible(false);
            idLimpar.setVisible(false);
            idCadastrar.setVisible(false);
            idRefresh.setVisible(false);
            
            //Habilita o menu de visualização
            idVoltar.setVisible(true);
            idAnchorPane.setVisible(true);
            idAtualizar.setVisible(false);

            //Pega as informações da linha tabela selecionada, e joga nos inputs para visualização
            idNome.setText(alunos.getNome());
            idEmail.setText(alunos.getEmail());
            idRg.setText(alunos.getRg());
            idCpf.setText(alunos.getCpf());
            idTelefone.setText(alunos.getTelefone());
            idCelular.setText(alunos.getCelular());
            idLogin.setText(alunos.getLogin());
            ObservableList<EnumEstado> estados = FXCollections.observableArrayList(EnumEstado.values());
            idEstado.setItems(estados);

            //Pega as informações da tabela Endereço
            idRua.setText(end.getRua());
            idNumero.setText(String.valueOf(end.getNumero()));
            idComplemento.setText(end.getComplemento());
            idCep.setText(end.getCep());
            idBairro.setText(end.getBairro());
            idCidade.setText(end.getCidade());
            idEstado.setValue(end.getEstado());

            //Desabilita a opção de edição dos campos (Only Read)
            idNome.setDisable(true);
            idEmail.setDisable(true);
            idRg.setDisable(true);
            idCpf.setDisable(true);
            idTelefone.setDisable(true);
            idCelular.setDisable(true);
            idLogin.setDisable(true);
            idSenha.setDisable(true);
            idEstado.setDisable(true);
            idRua.setDisable(true);
            idNumero.setDisable(true);
            idComplemento.setDisable(true);
            idCep.setDisable(true);
            idBairro.setDisable(true);
            idCidade.setDisable(true);
            idDataNascimento.setDisable(true);
        }catch(Exception e){
            Alert alert2 = new Alert(AlertType.WARNING, "Por favor, selecione um registro!");
            alert2.showAndWait();
        }
    }
    
    @FXML
    public void refreshTable(ActionEvent event){
        DAOAluno daoAluno = new DAOAluno();
        idTable.getItems().clear();
        idTable.getItems().addAll(daoAluno.listarTudo());
    }
    
    @FXML
    public void editar(ActionEvent event) throws IOException, NoSuchAlgorithmException, UnsupportedEncodingException, ParseException{
            try{
                //Inicializa a operação de edição do dado selecionado na tabela
                TablePosition pos = idTable.getSelectionModel().getSelectedCells().get(0);
                int getRow = pos.getRow();              //Pega o índice da linha selecionada
                int getRowEndereco = pos.getRow();
                
                list = new ArrayList();             //Array que vai armazenar as chaves primárias do banco
                array = new ArrayList();
                
                DAOAluno daoAluno = new DAOAluno();
                List<Aluno> aluno = daoAluno.listarAluno();
                for(Aluno alunos: aluno){
                    getRow = alunos.getCodigo();      //Faz as linhas da tabela serem iguais as chaves primárias do banco
                    list.add(getRow);               //Adiciona no Array as chaves primárias
                }

                //Obtem um elemento pela id no banco, no caso, o valor do índice que eu selecionar na tabela vai representar essa id
                int row = pos.getRow();  
                Aluno alunos = daoAluno.alterarAluno((int) list.get(row));

                //TESTANDO//
                DAOEndereco daoEndereco = new DAOEndereco();
                List<Endereco> endereco = daoEndereco.listarEnderecoAluno();
                for(Endereco end: endereco){
                    getRowEndereco = end.getCodigo_aluno();
                    array.add(getRowEndereco);              
                }
                Endereco end = daoEndereco.alterarEndereco((int) array.get(row));
                
                //
                idNome.setDisable(false);
                idEmail.setDisable(false);
                idRg.setDisable(false);
                idCpf.setDisable(false);
                idTelefone.setDisable(false);
                idCelular.setDisable(false);
                idLogin.setDisable(false);
                idSenha.setDisable(false);
                idEstado.setDisable(false);
                idRua.setDisable(false);
                idNumero.setDisable(false);
                idComplemento.setDisable(false);
                idCep.setDisable(false);
                idBairro.setDisable(false);
                idCidade.setDisable(false);
                idDataNascimento.setDisable(false);

                //Esconde o menu de gerenciamento
                idGrid.setVisible(false);
                idFechar.setVisible(false);
                idEditar.setVisible(false);
                idExcluir.setVisible(false);
                idTable.setVisible(false);
                idVisualizar.setVisible(false);
                idCadastrar.setVisible(false);
                idVoltar.setVisible(false);
                idRefresh.setVisible(false);
                
                //Habilita o menu de edição
                idAnchorPane.setVisible(true);
                idAtualizar.setVisible(true);
                idCancelar.setVisible(true);
                idTeclado.setVisible(true);
                idLimpar.setVisible(true);

                //Pega as informações da linha tabela selecionada, e joga nos inputs para edição
                idNome.setText(alunos.getNome());
                idEmail.setText(alunos.getEmail());
                idRg.setText(alunos.getRg());
                idCpf.setText(alunos.getCpf());
                idTelefone.setText(alunos.getTelefone());
                idCelular.setText(alunos.getCelular());
                idLogin.setText(alunos.getLogin());
                ObservableList<EnumEstado> estados = FXCollections.observableArrayList(EnumEstado.values());
                idEstado.setItems(estados);

                //TESTANDO//Pega as informações da tabela Endereço
                idRua.setText(end.getRua());
                idNumero.setText(String.valueOf(end.getNumero()));
                idComplemento.setText(end.getComplemento());
                idCep.setText(end.getCep());
                idBairro.setText(end.getBairro());
                idCidade.setText(end.getCidade());
                idEstado.setValue(end.getEstado());
                
                
                //Evento para o botão atualizar, executa a operação de UPDATE no banco após fazer as alterações
                idAtualizar.setOnAction(e -> {
                    if(idNome.getText().isEmpty() || idEmail.getText().isEmpty() || idRg.getText().isEmpty() ||
                        idCpf.getText().isEmpty() || idTelefone.getText().isEmpty() || idCelular.getText().isEmpty() || 
                        idLogin.getText().isEmpty() || idSenha.getText().isEmpty() || idDataNascimento.getValue() == null || 
                        idRua.getText().isEmpty() || idNumero.getText().isEmpty() || idCep.getText().isEmpty() || 
                        idBairro.getText().isEmpty() || idCidade.getText().isEmpty() || idEstado.getValue() == null){
                            Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
                            alert.showAndWait();
                    }else{
                        Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente atualizar?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                        alert.showAndWait();
                        if(alert.getResult() == ButtonType.YES){
                            alunos.setNome(idNome.getText());
                            alunos.setEmail(idEmail.getText());
                            alunos.setRg(idRg.getText());
                            alunos.setCpf(idCpf.getText());
                            alunos.setTelefone(idTelefone.getText());
                            alunos.setCelular(idCelular.getText());
                            alunos.setLogin(idLogin.getText());
                            alunos.setDataNascimento(java.sql.Date.valueOf(idDataNascimento.getValue()));
                            
                            end.setRua(idRua.getText());
                            end.setNumero(Integer.parseInt(idNumero.getText()));
                            end.setComplemento(idComplemento.getText());
                            end.setCep(idCep.getText());
                            end.setBairro(idBairro.getText());
                            end.setCidade(idCidade.getText());
                            end.setEstado(idEstado.getValue());
                            try {
                                String senha = daoAluno.criptografarSenha(idSenha.getText()); //Criptografar senha alterada do aluno
                                alunos.setSenha(senha);
                            } catch (NoSuchAlgorithmException ex) {
                                ex.printStackTrace();
                            } catch (UnsupportedEncodingException ex) {
                                ex.printStackTrace();
                            }
                            
                            //Atualiza no banco
                            daoAluno.cadastrarAluno(alunos);
                            daoEndereco.cadastrarEndereco(end);

                            //Mensagem de sucesso na operação
                            Alert alert2 = new Alert(AlertType.INFORMATION, "Dados atualizados com sucesso!");
                            alert2.showAndWait();

                            //Esconde o menu de edição
                            idAnchorPane.setVisible(false);

                            //Habilita novamente o menu de gerenciamento
                            idGrid.setVisible(true);
                            idFechar.setVisible(true);
                            idEditar.setVisible(true);
                            idExcluir.setVisible(true);
                            idTable.setVisible(true);
                            idVisualizar.setVisible(true);
                            idCadastrar.setVisible(true);
                            idRefresh.setVisible(true);

                            //Da um refresh na tabela para mostrar o dado já atualizado
                            idTable.getItems().clear();
                            idTable.getItems().addAll(daoAluno.listarTudo());
                        }if(alert.getResult() == ButtonType.NO){
                            alert.close();
                        }if(alert.getResult() == ButtonType.CANCEL){
                            alert.close();
                        }
                    }});
                }catch(Exception e){
                    Alert alert2 = new Alert(AlertType.WARNING, "Por favor, selecione um registro!");
                    alert2.showAndWait();
                }
    }
    
    @FXML
    public void excluir(ActionEvent event){
        try{
            //Inicializa a operação de exclusão do dado selecionado
            TablePosition pos = idTable.getSelectionModel().getSelectedCells().get(0);
            int getRow = pos.getRow();                  //Pega o índice da linha selecionada
            list = new ArrayList();                     //Array que vai armazenar as chaves primárias do banco

            DAOEndereco daoEndereco = new DAOEndereco();
            DAOAluno daoAluno = new DAOAluno();

            List<Aluno> aluno = daoAluno.listarAluno();
            for(Aluno prof: aluno){
                getRow = prof.getCodigo();              
                list.add(getRow);                       
            }

            List<Endereco> endereco = daoEndereco.listarEnderecoAluno();
            for(Endereco end: endereco){
                getRow = end.getCodigo();                   //Faz as linhas da tabela serem iguais as chaves primárias do banco
                list.add(getRow);                           //Adiciona no Array as chaves primárias
            }
            int row = pos.getRow();                         //Pega novamente os índices da tabela

            Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente excluir?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                daoEndereco.remover((int) list.get(row));       //Deleta primeiro o endereço, por conter uma restrinção de dependência com Aluno, Exclui o ítem de acordo com o índice do array, que corresponde exatamente as chaves primárias no bando
                //daoAluno.remover((int) list.get(row));    //Não se faz necessário

                //Mensagem de confirmação
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Excluído com sucesso!");
                alert2.showAndWait();

                //Da um refresh na tabela para tirar o dado que foi removido
                idTable.getItems().clear();
                idTable.getItems().addAll(daoAluno.listarTudo());
            }if(alert.getResult() == ButtonType.NO){
                alert.close();
            }if(alert.getResult() == ButtonType.CANCEL){
                alert.close();
            }
        }catch(Exception e){
            Alert alert2 = new Alert(AlertType.WARNING, "Por favor, selecione um registro!");
            alert2.showAndWait();
        }
        
    }
    
    //Tudo que estiver nesse método é executado ao abrir a janela
    @Override
    public void initialize(URL AlunoConsultar, ResourceBundle rb){
        //Carrega os dados do banco na tabela
        idAnchorPane.setVisible(false);
        DAOAluno daoAluno = new DAOAluno();
        nome.setCellValueFactory(new PropertyValueFactory ("nome"));
        email.setCellValueFactory(new PropertyValueFactory ("email"));
        data_matricula.setCellValueFactory(new PropertyValueFactory ("dataMatricula"));
        celular.setCellValueFactory(new PropertyValueFactory ("celular"));
        telefone.setCellValueFactory(new PropertyValueFactory ("telefone"));
        rg.setCellValueFactory(new PropertyValueFactory ("rg"));
        cpf.setCellValueFactory(new PropertyValueFactory ("cpf"));
        dataNascimento.setCellValueFactory(new PropertyValueFactory ("dataNascimento"));
        
        idTable.getItems().addAll(daoAluno.listarTudo());
    } 


}
