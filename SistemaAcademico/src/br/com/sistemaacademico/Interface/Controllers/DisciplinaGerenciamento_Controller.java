package br.com.sistemaacademico.Interface.Controllers;

import br.com.sistemaacademico.DAO.DAODisciplina;
import br.com.sistemaacademico.Entity.Disciplina;
import br.com.sistemaacademico.Entity.EnumPeriodo;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DisciplinaGerenciamento_Controller implements Initializable {

    @FXML private Button idEditar;
    @FXML private Button idExcluir;
    @FXML private Button idFechar;
    @FXML private Button idLimpar;
    @FXML public TableView<Disciplina> idTable;
    @FXML private TableColumn nome;
    @FXML private TableColumn periodo;
    @FXML private AnchorPane idAnchorPaneGerenciar;
    @FXML private GridPane idGrid;

    @FXML private AnchorPane idAnchorPane;
    @FXML private ComboBox<EnumPeriodo> idPeriodo;
    @FXML private TextField idNome;
    @FXML private Button idAtualizar;       
    @FXML private Button idCancelar;
    @FXML private Button idTeclado;
    @FXML private Button idCadastrar;
    @FXML private Button idRefresh;
    
        
    public List list = null;
    public List array = null;
    
    @FXML
    public void limpar(ActionEvent event){
        //LIMPAR CAMPOS
        idNome.clear();
    }
    
    @FXML
    public void atualizar(ActionEvent event){
        //Operação utilizada mais abaixo
    }
    
    @FXML
    public void cadastrar(ActionEvent event) throws IOException{
        //ABRIR TELA DE CADASTRO DE PROFESSOR
        Stage stage = new Stage();
        stage.setTitle("JM@cade - Cadastrar Disciplina");
        Parent telaAluno = FXMLLoader.load(getClass().getResource("/br/com/sistemaacademico/Interface/Views/DisciplinaCadastrar_View.fxml"));
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
    public void refreshTable(ActionEvent event){
        DAODisciplina daoDisciplina = new DAODisciplina();
        idTable.getItems().clear();
        idTable.getItems().addAll(daoDisciplina.listarTudo());
    }
    
    @FXML
    public void editar(ActionEvent event) throws IOException, NoSuchAlgorithmException, UnsupportedEncodingException, ParseException{
            try{
                //Inicializa a operação de edição do dado selecionado na tabela
                TablePosition pos = idTable.getSelectionModel().getSelectedCells().get(0);
                int getRow = pos.getRow();              //Pega o índice da linha selecionada

                list = new ArrayList();             //Array que vai armazenar as chaves primárias do banco

                DAODisciplina daoDisciplina = new DAODisciplina();
                List<Disciplina> disciplina = daoDisciplina.listarDisciplina();
                for(Disciplina disc: disciplina){
                    getRow = disc.getCodigo();      //Faz as linhas da tabela serem iguais as chaves primárias do banco
                    list.add(getRow);               //Adiciona no Array as chaves primárias
                }

                //Obtem um elemento pela id no banco, no caso, o valor do índice que eu selecionar na tabela vai representar essa id
                int row = pos.getRow();  
                Disciplina disciplinas = daoDisciplina.alterarDisciplina((int) list.get(row));

                idNome.setDisable(false);
                idPeriodo.setDisable(false);

                //Esconde o menu de gerenciamento
                idGrid.setVisible(false);
                idFechar.setVisible(false);
                idEditar.setVisible(false);
                idExcluir.setVisible(false);
                idTable.setVisible(false);
                idCadastrar.setVisible(false);
                idRefresh.setVisible(false);
                
                //Habilita o menu de edição
                idAnchorPane.setVisible(true);
                idAtualizar.setVisible(true);
                idCancelar.setVisible(true);
                idTeclado.setVisible(true);
                idLimpar.setVisible(true);

                //Pega as informações da linha tabela selecionada, e joga nos inputs para edição
                idNome.setText(disciplinas.getNome());
                ObservableList<EnumPeriodo> periodos = FXCollections.observableArrayList(EnumPeriodo.values());
                idPeriodo.setItems(periodos);
                idPeriodo.setValue(disciplinas.getPeriodo());
                
                
                //Evento para o botão atualizar, executa a operação de UPDATE no banco após fazer as alterações
                idAtualizar.setOnAction(e -> {
                    if(idNome.getText().isEmpty() || idPeriodo.getValue() == null){
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
                        alert.showAndWait();
                    }else{
                        Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente atualizar?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                        alert.showAndWait();
                        if(alert.getResult() == ButtonType.YES){
                            disciplinas.setNome(idNome.getText());
                            disciplinas.setPeriodo(idPeriodo.getValue());
                            
                            //Atualiza no banco
                            daoDisciplina.cadastrarDisciplina(disciplinas);
                            
                            //Mensagem de sucesso na operação
                            Alert alert2 = new Alert(AlertType.INFORMATION, "Dados atualizados com sucesso!");
                            alert2.showAndWait();

                            //Esconde o menu de edição
                            idAnchorPane.setVisible(false);

                            //Habilita novamente o menu de gerenciamento
                            idGrid.setVisible(true);
                            idCadastrar.setVisible(true);
                            idRefresh.setVisible(true);
                            idFechar.setVisible(true);
                            idEditar.setVisible(true);
                            idExcluir.setVisible(true);
                            idTable.setVisible(true);

                            //Da um refresh na tabela para mostrar o dado já atualizado
                            idTable.getItems().clear();
                            idTable.getItems().addAll(daoDisciplina.listarTudo());
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

            DAODisciplina daoDisciplina = new DAODisciplina();

            List<Disciplina> disciplina = daoDisciplina.listarDisciplina();
            for(Disciplina disc: disciplina){
                getRow = disc.getCodigo();              
                list.add(getRow);                       
            }

            int row = pos.getRow();                         //Pega novamente os índices da tabela

            Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente excluir?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                daoDisciplina.remover((int) list.get(row));       //Deleta primeiro o endereço, por conter uma restrinção de dependência com Disciplina, Exclui o ítem de acordo com o índice do array, que corresponde exatamente as chaves primárias no bando

                //Mensagem de confirmação
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Excluído com sucesso!");
                alert2.showAndWait();

                //Da um refresh na tabela para tirar o dado que foi removido
                idTable.getItems().clear();
                idTable.getItems().addAll(daoDisciplina.listarTudo());
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
    public void initialize(URL DisciplinaConsultar, ResourceBundle rb){
        //Carrega os dados do banco na tabela
        idAnchorPane.setVisible(false);
        DAODisciplina daoDisciplina = new DAODisciplina();
        nome.setCellValueFactory(new PropertyValueFactory ("nome"));
        periodo.setCellValueFactory(new PropertyValueFactory ("periodo"));
        
        idTable.getItems().addAll(daoDisciplina.listarTudo());
    } 


}
