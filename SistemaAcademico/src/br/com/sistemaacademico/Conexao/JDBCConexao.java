package br.com.sistemaacademico.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Bruno Felix
 */
public class JDBCConexao {
    
    private static JDBCConexao bdConecta = null;
    
    //Cria uma instancia de conexao, garante que a Classe tenha apenas uma instancia conexao
    public static JDBCConexao getInstance(){
        if(bdConecta == null){
            bdConecta = new JDBCConexao();
        }
        return bdConecta;
    }

    //Cria a conexao ao banco
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection conn = null;
        try{
            Class.forName ("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost:3306/sistemaacademico";
            String login = "root";
            String password = "";
            conn = DriverManager.getConnection(db, login, password);
            System.out.print("Conectado á: ");
        }catch(ClassNotFoundException erro){
            System.err.println("Nao encontrado!");
        }catch(SQLException erro){
            System.err.println("Erro de conexao com o banco de dados!");
        }
        return conn;
    }
    
    //Fechar conexao
    public static void fecharConexao(Connection conn) throws SQLException {
        try{
            conn.close();
        }
        catch(Exception ex) {
            throw new SQLException(ex.getMessage());
        }
  }
       
   
}
